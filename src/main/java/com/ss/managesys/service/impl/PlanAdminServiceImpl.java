package com.ss.managesys.service.impl;

import com.ss.excel.factory.SSExcel07Workbook;
import com.ss.excel.factory.sheet.SSExcel07Sheet;
import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.po.Product;
import com.ss.managesys.entity.vo.ExaminerVo;
import com.ss.managesys.entity.vo.InPlanAdmin;
import com.ss.managesys.entity.vo.PlanAdminCheckCardVo;
import com.ss.managesys.entity.vo.PlanAdminProdTaskVo;
import com.ss.managesys.entity.vo.tvo.ExaminerTvo;
import com.ss.managesys.entity.vo.tvo.PlanCardsChildrenTvo;
import com.ss.managesys.entity.vo.tvo.PlanCardsProdListDetailTvo;
import com.ss.managesys.entity.vo.tvo.PlanExcelTvo;
import com.ss.managesys.mapper.ExaminerMapper;
import com.ss.managesys.mapper.OprLeaderMapper;
import com.ss.managesys.mapper.PlanAdminMapper;
import com.ss.managesys.mapper.PushDataMapper;
import com.ss.managesys.push_data.PushDataMain;
import com.ss.managesys.push_data.PushProductMGNData;
import com.ss.managesys.push_data.PushTaskMGNData;
import com.ss.managesys.service.PlanAdminService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 计划管理员对任务和产品 的操作
 *
 * @author GuoYanSong
 * @Date 2023-4-12
 */
@Service
@Slf4j
public class PlanAdminServiceImpl implements PlanAdminService {
    @Value("${user.authOperator}")
    private Integer authOperator;
    @Value("${user.authExaminer}")
    private Integer authExaminer;
    @Value("${user.proShishiZhong}")
    private Integer proShishiZhong;

    @Resource
    private PlanAdminMapper planAdminMapper;
    @Resource
    private ExaminerMapper examinerMapper;
    @Resource
    private PushDataMapper pushDataMapper;
    @Resource
    private PushDataMain pushDataMain;
    @Resource
    private OprLeaderMapper oprLeaderMapper;

    /**
     * 【计划管理员创建新产品】
     * 1、上传保存excel文件；
     * 2、解析excel文件内容返回；
     * 3、将excel文件内容存入；
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = -1)
    public RespondDto panNewProd(String prodModel, String prodStage, String prodDirector,
                                 Integer creatorID, MultipartFile multipartFile) {


        Integer integer = planAdminMapper.selectIfHave(prodModel);
        if(integer != 0){
            return new RespondDto(ResultCode.NO, "新增失败  已存在该产品型号");
        }
//       保存文件到本地
        File dir = new File("/root/uploadFile/excel");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatted = current.format(formatter);

        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        File file = new File(dir.getAbsolutePath() + File.separator + formatted + "-" + end3 + "-" + multipartFile.getOriginalFilename());
        try {
            multipartFile.transferTo(file);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        log.info("【上传文件》文件已保存到本地:{}】", file.getAbsolutePath());
        //        获取excel文件内容

        /*1、planExcel 新增属性顺序号；
            for select 是不是组长；
        * 2、存入pord_task_user 时将顺序号也一并存入；
        3、把第一条任务设置为当前任务；*/
        ArrayList<PlanExcelTvo> planExcelTvos = readExcel(file.getAbsolutePath());

        for (int i = 1; i <= planExcelTvos.size(); i++) {
            Integer userAuth = oprLeaderMapper.selectUserAuthByName(planExcelTvos.get(i-1).getOperator());
            if(userAuth == null || userAuth != 3){
                return new RespondDto(ResultCode.NO, "新增失败，第"+i+"行操作员不是组长");
            }
        }

        planAdminMapper.insertProd(prodModel, prodStage, prodDirector, creatorID);
        planAdminMapper.insertTask(prodModel, planExcelTvos);

//        System.out.println("planexcelTvosSize:" + planExcelTvos.size());
        for (PlanExcelTvo planExcelTvo : planExcelTvos) {

            String allOperator = planExcelTvo.getOperator();
            String allExaminer = planExcelTvo.getExaminer();
//            ArrayList<String> operators = new ArrayList<>();
//            ArrayList<String> examiners = new ArrayList<>();
            String[] operatorLists = null;
            String[] examinerLists = null;

            if (allOperator.contains("、") || allExaminer.contains("、")) {
//                System.out.println("、 进入");
                operatorLists = allOperator.split("、");
//                for (String operatorList : operatorLists) {
//                    System.out.println("operatorList------------:"+operatorList);
//                }
                for (int i = 0; i < operatorLists.length; i++) {
                    Integer operatorUserId = planAdminMapper.selectUserIDByUserName(operatorLists[i]);
                    operatorLists[i] = operatorUserId.toString();
                }
//                for (String operatorList : operatorLists) {
//                    System.out.println("operatorList------------:" + operatorList);
//                }

                examinerLists = allExaminer.split("、");
                for (int i = 0; i < examinerLists.length; i++) {
                    Integer examinerUserId = planAdminMapper.selectUserIDByUserName(examinerLists[i]);
                    examinerLists[i] = examinerUserId.toString();
                }
//                operators = new ArrayList<String>(Arrays.asList());
//                operators = new ArrayList<String>(Arrays.asList());

//                examiners = new ArrayList<String>(Arrays.asList(allExaminer.split("、")));
//                System.out.println("operators"+operators);
//                System.out.println("examiners"+examiners);
            } else if (allOperator.contains(",") || allExaminer.contains(",")) {
                operatorLists = allOperator.split(",");
                for (int i = 0; i < operatorLists.length; i++) {
                    Integer operatorUserId = planAdminMapper.selectUserIDByUserName(operatorLists[i]);
                    operatorLists[i] = operatorUserId.toString();
                }

                examinerLists = allExaminer.split(",");
                for (int i = 0; i < examinerLists.length; i++) {
                    Integer examinerUserId = planAdminMapper.selectUserIDByUserName(examinerLists[i]);
                    examinerLists[i] = examinerUserId.toString();
                }
//                operators = new ArrayList<String>(Arrays.asList(allOperator.split(",")));
//                examiners = new ArrayList<String>(Arrays.asList(allExaminer.split(",")));
//                System.out.println("operators"+operators);
//                System.out.println("examiners"+examiners);
            } else if (allOperator.contains("，") || allExaminer.contains("，")) {
                operatorLists = allOperator.split("，");
                for (int i = 0; i < operatorLists.length; i++) {
                    Integer operatorUserId = planAdminMapper.selectUserIDByUserName(operatorLists[i]);
                    operatorLists[i] = operatorUserId.toString();
                }

                examinerLists = allExaminer.split("，");
                for (int i = 0; i < examinerLists.length; i++) {
                    Integer examinerUserId = planAdminMapper.selectUserIDByUserName(examinerLists[i]);
                    examinerLists[i] = examinerUserId.toString();
                }
//                operators = new ArrayList<String>(Arrays.asList(allOperator.split("，")));
//                examiners = new ArrayList<String>(Arrays.asList(allExaminer.split("，")));
//                System.out.println("operators"+operators);
//                System.out.println("examiners"+examiners);
            } else if (!allOperator.contains("、") && !allExaminer.contains("、") && !allOperator.contains(",") && !allExaminer.contains(",")
                    && !allOperator.contains("，") && !allExaminer.contains("，")) {
//                System.out.println("进入全部不包含了");
                operatorLists = new String[1];
                examinerLists = new String[1];
                Integer operatorUserId = planAdminMapper.selectUserIDByUserName(allOperator);
                operatorLists[0] = operatorUserId.toString();
                Integer examinerUserId = planAdminMapper.selectUserIDByUserName(allExaminer);
                examinerLists[0] = examinerUserId.toString();
            }

//            int testint = 1;
//            System.out.println("第" + (testint++) + "次循环：" + planExcelTvo.getTaskName());
//

//            for (String operator : operators) {
            Integer prodAutoId = planAdminMapper.selectProdInsertProdAutoId(prodModel);
            for (String operator : operatorLists) {
//                planAdminMapper.insertPrTaUser(prodModel, planExcelTvo.getTaskName(), operator, authOperator);if(){
                planAdminMapper.insertptoOPrEx(prodAutoId, planExcelTvo.getTaskName(),
                        Integer.valueOf(operator), authOperator,planExcelTvo.getOrderNum());
                if(planExcelTvo.getOrderNum() == 1){
                    /*设置第一条任务为当前任务*/
                    oprLeaderMapper.updateTaskNow(prodModel,planExcelTvo.getTaskName());
                }
            }
            for (String examiner : examinerLists) {
                planAdminMapper.insertptoOPrEx(prodAutoId, planExcelTvo.getTaskName(),
                        Integer.valueOf(examiner), authExaminer,planExcelTvo.getOrderNum());
            }

//            planAdminMapper.insertProdTaskUser(prodModel, planExcelTvo.getTaskName(), operators, authOperator);
//            planAdminMapper.insertProdTaskUser(prodModel, planExcelTvo.getTaskName(), examiners, authExaminer);
        }

        /*--------hprose注释了---------*/
//        ArrayList<PushTaskMGNData> pushTaskMGNData = new ArrayList<>();
//        for (PlanExcelTvo planExcelTvo : planExcelTvos) {
//            pushTaskMGNData.add(new PushTaskMGNData(planExcelTvo.getTaskName(),"未实施"));
//        }
//        pushDataMain.hprosePush(new PushProductMGNData(prodModel,prodStage,prodDirector,"未实施",pushTaskMGNData));
        return new RespondDto(ResultCode.OK, "新增产品成功");
    }

    /**
     * 【计划管理员分页获取产品列表与任务列表】
     * 计划管理员：产品》任务
     * 1、展示所有未删除的产品以及产品对应的未删除的任务；
     * 2、关键字搜索，联合prod 和 task ；
     * 查出 产品型号 依据产品型号查找对应任务
     */
    @Override
    public RespondDto showProdAndTaskLists(@NonNull InPlanAdmin inPlanAdmin) {

        //        拼sql时防止拼null
        if (inPlanAdmin.getCurrentPage() == null || inPlanAdmin.getPageSize() == null) {
            return new RespondDto(ResultCode.NO, "请检查是否成功传入CurrentPage和PageSize");
        }
        if (inPlanAdmin.getKeywords() == null) {
            inPlanAdmin.setKeywords("");
        }


        Integer limitIndex = (inPlanAdmin.getCurrentPage() - 1) * inPlanAdmin.getPageSize();
        Integer limitEnd = inPlanAdmin.getCurrentPage() * inPlanAdmin.getPageSize();

        ArrayList<PlanCardsProdListDetailTvo> planCardsProdListDetailTvos = planAdminMapper.selectProd();

//        System.out.println("224selectProd: 查找到的产品对应的：" + planCardsProdListDetailTvos);

        /*planCardsProdListDetailTvos.size() 产品数量*/
        Integer id = 1;
        ArrayList<PlanCardsProdListDetailTvo> nullReturnProdListDetail = new ArrayList<>();
        if (planCardsProdListDetailTvos.size() == 0) {
            return new RespondDto(ResultCode.OK, "查询成功，没有产品和任务", new PlanAdminProdTaskVo(0, nullReturnProdListDetail));
        } else if (planCardsProdListDetailTvos.size() > 0) {

            for (PlanCardsProdListDetailTvo planCardsProdListDetailTvo : planCardsProdListDetailTvos) {

                planCardsProdListDetailTvo.setId(id++);
                /*依据产品名查出对应的任务children*/
                ArrayList<PlanCardsChildrenTvo> planCardsChildrenTvos =
                        planAdminMapper.selectTask(planCardsProdListDetailTvo.getProdModel(), inPlanAdmin.getKeywords());

//                System.out.println("239 children 查找到的 planCardsChildrenTvos： "+planCardsChildrenTvos);

                /*替换task中为检验员为null的项 赋值检验员名*/
                for (int i = 0; i < planCardsChildrenTvos.size(); i++) {

                    planCardsChildrenTvos.get(i).setId(id++);

                    /*检验员为空时， 按照taskname 、prodId 为条件 查询对应任务所需的人*/
                    if (planCardsChildrenTvos.get(i) != null) {
                        if (planCardsChildrenTvos.get(i).getOperator() == null) {
//                            System.out.println("247开始尝试给为null的children赋值");
                            ExaminerTvo operatore = planAdminMapper.selectTaskOperators(planCardsChildrenTvos.get(i).getTaskName(),
                                    planCardsProdListDetailTvo.getProdId(), authOperator);
//                            System.out.println("任务名planCardsChildrenTvos.get(i).getTaskName()："+planCardsChildrenTvos.get(i).getTaskName());
//                            System.out.println("任务id planCardsChildrenTvos.get(i).getId()："+planCardsChildrenTvos.get(i).getId());
//                            System.out.println("250:展示没有实际操作员时找到的对应的操作员："+operatore);
                            if (operatore != null) {
                                planCardsChildrenTvos.get(i)
                                        .setOperator(operatore.getOperator());
                            }

                        }
                    }

                }

                /*产品下没有对应任务删除产品 ？*/
                planCardsProdListDetailTvo.setChildren(planCardsChildrenTvos);
//                if(planCardsChildrenTvos.size() == 0){
//                    planCardsProdListDetailTvos.remove(planCardsProdListDetailTvo);
//                }else {
//                    planCardsProdListDetailTvo.setChildrenTvos(planCardsChildrenTvos);
//                }
            }


            /*检查起始页码index和end页码*/
            Integer totalDataNum = planCardsProdListDetailTvos.size();
            if (limitIndex > totalDataNum) {
                return new RespondDto(ResultCode.NO, "请检查传入起始页数是否正确", new PlanAdminProdTaskVo(0, nullReturnProdListDetail));
            } else if (limitEnd >= totalDataNum) {
                List<PlanCardsProdListDetailTvo> returnplanCardsProdListDetailTvos = planCardsProdListDetailTvos.subList(limitIndex, planCardsProdListDetailTvos.size());
                return new RespondDto(ResultCode.OK, "计划管理员产品任务列表查询成功", new PlanAdminProdTaskVo(totalDataNum, returnplanCardsProdListDetailTvos));
            } else if (limitEnd < totalDataNum) {
                List<PlanCardsProdListDetailTvo> returnplanCardsProdListDetailTvos = planCardsProdListDetailTvos.subList(limitIndex, limitEnd);
                return new RespondDto(ResultCode.OK, "计划管理员产品任务列表查询成功", new PlanAdminProdTaskVo(totalDataNum, returnplanCardsProdListDetailTvos));
            } else {
                return new RespondDto(ResultCode.NO, "分页搜索展示失败" ,new PlanAdminProdTaskVo(0, nullReturnProdListDetail));
            }

        } else {
            return new RespondDto(ResultCode.NO, "查询失败", new PlanAdminProdTaskVo(0, nullReturnProdListDetail));
        }

    }

    /**
     * 【查看任务检验单】
     * 1、依据产品型号在prod 找到 未删除的已下发的产品型号和产品阶段；
     * 2、以该产品型号在task 找软任务名称、状态、创建日期
     */
    @Override
    public RespondDto showCheckCard(@NonNull InPlanAdmin inPlanAdmin) {
        if (inPlanAdmin.getProdModel() != null) {
            ArrayList<PlanAdminCheckCardVo> planAdminCheckCardVos = planAdminMapper.selectCheckCard(inPlanAdmin.getProdModel());
            if (planAdminCheckCardVos.size() == 0) {
                return new RespondDto(ResultCode.OK, "没有符合要求的检验单", planAdminCheckCardVos);
            } else {
                return new RespondDto(ResultCode.OK, "查看任务检验单成功", planAdminCheckCardVos);
            }
        } else {
            return new RespondDto(ResultCode.NO, "入参prodModel为null值");
        }
    }

    /**
     * 【下发产品任务】
     * 1、修改下发 状态 ；
     * 2、修改 产品状态为实施中
     */
    @Override
    public RespondDto updateProdSend(@NonNull InPlanAdmin inPlanAdmin) {
        if (inPlanAdmin.getProdModel() != null) {

            ArrayList<PushTaskMGNData> pushTaskMGNData = pushDataMapper.selectPushTaskMGNData(inPlanAdmin.getProdModel());
            Product product = planAdminMapper.selectProdIfSend(inPlanAdmin.getProdModel());
            if (product.getIfsend() == 1) {
                return new RespondDto(ResultCode.OK, "产品任务已被下发");
            } else {
                Integer prodSendNum = planAdminMapper.updateProdSendAndStatus(inPlanAdmin.getProdModel(), proShishiZhong);

//            Integer prodStatusSum = planAdminMapper.updateProdStatus(inPlanAdmin.getProdModel(), proShishiZhong);
//                if (prodSendNum == 1) {

                /*--------hprose注释了---------*/
//                    pushDataMain.hprosePush(new PushProductMGNData(inPlanAdmin.getProdModel(),product.getProdStage(),
//                            product.getProdDirector(),"实施中",pushTaskMGNData));
                    return new RespondDto(ResultCode.OK, "产品任务下发成功");
//                } else {
//                    return new RespondDto(ResultCode.NO, "没有产品任务被下发");
//                }
            }
        } else {
            return new RespondDto(ResultCode.NO, "入参prodModel为null值");
        }

    }

    /**
     * 【删除产品】
     */
    @Override
    public RespondDto deleteProd(@NonNull InPlanAdmin inPlanAdmin) {
        if (inPlanAdmin.getProdModel() != null) {
            Integer integer = planAdminMapper.updateProdDelete(inPlanAdmin.getProdModel());
            if (integer == 1) {

                /*--------hprose注释了---------*/
//                PushProductMGNData pushProductMGNData = pushDataMapper.selectPushProductMGNData(inPlanAdmin.getProdModel());
//                pushProductMGNData.setProdStatus("已删除");
//                pushDataMain.hprosePush(pushProductMGNData);
                return new RespondDto(ResultCode.OK, "产品删除改成功");
            } else {
                return new RespondDto(ResultCode.NO, "没有产品被删除");
            }
        } else {
            return new RespondDto(ResultCode.NO, "入参prodModel为null值");
        }
    }

    /**
     * 抽出的 【读取excel文件内容的方法】
     * <p>
     * 1、工作区间 -> Sheet -> cell；
     * 2、每行为一个 jxContent 对象， 把所有对象用 ArrayList保存;
     * 从第二行开始读取，第一行表头忽略；
     *
     * @param filePath ;本地保存后的文件地址
     * @return ArrayList<JxContent>
     */
    private ArrayList<PlanExcelTvo> readExcel(String filePath) {
        SSExcel07Sheet sheet = (new SSExcel07Workbook()).open(filePath).getSheet(0);
        ArrayList<PlanExcelTvo> PlanExcelTvos = new ArrayList<>();

        log.info("【readExcel方法开始：】");
        //外层控制行，每行为一个jxContent对象
        for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
            PlanExcelTvo planExcelTvo = new PlanExcelTvo();
            //内层各项为各列属性值
            for (int j = 0; j <= 4; j++) {
                switch (j) {
                    case 0:
                        planExcelTvo.setTaskName(String.valueOf(sheet.getCell(i, j).getCellValue()));
                        break;
                    case 1:
                        planExcelTvo.setOperator(String.valueOf(sheet.getCell(i, j).getCellValue()));
                        break;
                    case 2:
                        planExcelTvo.setPlanDay(Integer.valueOf((String) sheet.getCell(i, j).getCellValue()));
                        break;
                    case 3:
                        planExcelTvo.setExaminer(String.valueOf(sheet.getCell(i, j).getCellValue()));
                        break;
                    default:
//                        log.info("执行了default");
                        break;
                }
            }
            planExcelTvo.setOrderNum(i);
            PlanExcelTvos.add(planExcelTvo);
        }
        log.info("【上传文件：】excel：内容：{}");
        return PlanExcelTvos;
    }
}
