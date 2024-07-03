package com.ss.managesys.service.impl;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.po.Task;
import com.ss.managesys.entity.to.ProdTaskUserTo;
import com.ss.managesys.entity.vo.ExaminerVo;
import com.ss.managesys.entity.vo.InExaminer;
import com.ss.managesys.entity.vo.OperatorVo;
import com.ss.managesys.entity.vo.tvo.ExaminerTvo;
import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import com.ss.managesys.entity.vo.tvo.TaskAndProd;
import com.ss.managesys.mapper.ExaminerMapper;
import com.ss.managesys.mapper.OperatorMapper;
import com.ss.managesys.mapper.OprLeaderMapper;
import com.ss.managesys.push_data.PushDataMain;
import com.ss.managesys.service.ExaminerService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 检验员对产品任务的操作
 *
 * @author GuoYanSong
 * @Date 2023-4-12
 */
@Service
@Slf4j
public class ExaminerServiceImpl implements ExaminerService {

    @Value("${user.authOperator}")
    private Integer authOperator;
    @Value("${user.authExaminer}")
    private Integer authExaminer;

    @Resource
    private OperatorMapper operatorMapper;

    @Resource
    private ExaminerMapper examinerMapper;
    @Resource
    private PushDataMain pushDataMain;
    @Resource
    private OprLeaderMapper oprLeaderMapper;


    /**
     * 【第三方检验员任务单列表】
     * 1、依据检验员id确定任务 ；
     */
    @Override
    public RespondDto showAllExaminerTask(@NonNull InExaminer inExaminer) {

        //        拼sql时防止拼null
        if (inExaminer.getCurrentPage() == null || inExaminer.getPageSize() == null) {
            return new RespondDto(ResultCode.NO, "请检查是否成功传入CurrentPage和PageSize");
        }
        if (inExaminer.getKeywords() == null) {
            inExaminer.setKeywords("");
        }
        /*查出的产品对应的任务 任务名 task_name 列表
        *       后续用该任务名列表作为条件查询*/
//        ArrayList<String> taskNames = operatorMapper.selectTaskName(inExaminer.getExaminerID(), authExaminer, inExaminer.getKeywords());
        ArrayList<TaskAndProd> taskNameAndProds = operatorMapper.selectTaskAndProd(inExaminer.getExaminerID(), authExaminer, inExaminer.getKeywords());
//        System.out.println("60--taskNames:"+taskNameAndProds);

//        Integer totalDataNum = taskNames.size();  直接返回了数组的大小
        Integer limitIndex = (inExaminer.getCurrentPage() - 1) * inExaminer.getPageSize();
        Integer limitEnd = inExaminer.getCurrentPage() * inExaminer.getPageSize();


        /*数据为空时 必须返回的空数组  return*/
        List<ExaminerTvo> nullExaminerTvos = new ArrayList<>();

        if (taskNameAndProds.size() > 0) {

            ArrayList<ExaminerTvo> examinerTvos = examinerMapper.selectTaskExamCards(taskNameAndProds);
//            System.out.println("69查出的examinerTvos："+examinerTvos);
            ArrayList<ExaminerTvo> operatores = examinerMapper.selectTaskOperators(taskNameAndProds, authOperator);
//            System.out.println("71查出的operatores："+operatores);

            /*替换task中为检验员为null的项 赋值检验员名和id*/
            for (int i = 0; i < examinerTvos.size(); i++) {
                if (examinerTvos.get(i).getOperatorIds() == null) {
                    examinerTvos.get(i).setOperatorIds(operatores.get(i).getOperatorIds());
                    examinerTvos.get(i).setOperator(operatores.get(i).getOperator());
                }
            }

            if (limitIndex > examinerTvos.size()) {
                return new RespondDto(ResultCode.NO, "请检查传入起始页数是否正确",new ExaminerVo(0, nullExaminerTvos));
            } else if (limitEnd >= examinerTvos.size()) {
                List<ExaminerTvo> returnExaminerTvos = examinerTvos.subList(limitIndex, examinerTvos.size());
                return new RespondDto(ResultCode.OK, "检验员产品任务列表查询成功", new ExaminerVo(examinerTvos.size(), returnExaminerTvos));
            } else if (limitEnd < examinerTvos.size()) {
                List<ExaminerTvo> returnExaminerTvos = examinerTvos.subList(limitIndex, limitEnd);
                return new RespondDto(ResultCode.OK, "检验员产品任务列表查询成功", new ExaminerVo(examinerTvos.size(), returnExaminerTvos));
            } else {
                return new RespondDto(ResultCode.NO, "分页搜索展示失败",new ExaminerVo(0, nullExaminerTvos));
            }
        } else {
            return new RespondDto(ResultCode.OK, "该检验员暂时没有产品检验任务",new ExaminerVo(0, nullExaminerTvos));
        }
    }

    /**
     * 【第三方检验员出具检验单】
     * 4-14 单个出具改为多个
     * 1、检查多个任务是否全部被出具过；
     * 2、修改出具状态；
     */
    @Override
    public RespondDto creatCheckedCard(@NonNull InExaminer inExaminer) {
        log.info("oID{},eID{},pM{},tN{},ES{}", inExaminer.getOperatorID(), inExaminer.getExaminerID(), inExaminer.getProdModel(), inExaminer.getTaskName());
        if (inExaminer.getPtos() != null && inExaminer.getPtos().size() > 0) {
            Integer selectHadSize = examinerMapper.selectTaskList(inExaminer.getPtos());
//            System.out.println("114行  对比是否已被出示"+selectHadSize + "..." + inExaminer.getPtos().size());
            if (inExaminer.getPtos().size() == selectHadSize) {
                return new RespondDto(ResultCode.OK, "重复出具，该检验单已经被出具过");
            } else {
                Integer integer = examinerMapper.updateTaskCheckCard(inExaminer.getPtos(), inExaminer.getExaminerID());
//                log.info("测试员id:{};;;",inExaminer.getExaminerID());
                if (integer >= 1) {
                    return new RespondDto(ResultCode.OK, "检验员 出具检验单成功");
                } else if (integer == 0) {
                    return new RespondDto(ResultCode.NO, "核验检验和操作人员是否匹配");
                } else {
                    return new RespondDto(ResultCode.NO, "检验员 出具检验单失败");
                }
            }
        } else {
            return new RespondDto(ResultCode.NO, "检验员 出具检验单失败,ptos参数为null");

        }


//        Task task = operatorMapper.selectTask(inExaminer.getProdModel(), inExaminer.getTaskName());
//        if(task != null){
//            /*检查是否已经被出具*/
//            if(task.getIfCheckCard() == 1){
//                return new RespondDto(ResultCode.OK,"重复出具，该检验单已经被出具过");
//            }else {
//                Integer integer = examinerMapper.updateTaskCheckCard(inExaminer.getOperatorID(), inExaminer.getExaminerID(), inExaminer.getProdModel(), inExaminer.getTaskName());
//                if (integer == 1){
//                    return new RespondDto(ResultCode.OK,"检验员 出具检验单成功");
//                }else {
//                    return new RespondDto(ResultCode.NO,"没有检验单被出具，提醒核验数据库检验和操作人员是否匹配");
//                }
//            }
//        }else {
//            return new RespondDto(ResultCode.NO,"检验员 出具检验单失败");
//        }
    }

    /**
     * 【第三方检验员判断任务是否合格】
     * 1、先查找，确保任务可以被找到(没删除）；
     * 2、修改状态，可多次修改；
     * <p>
     * 设置传任务状态： 传递的检验状态为2合格，为3不合格
     *         设置任务 检验员为 实际操作检验员
     */
    @Override
    public RespondDto ifPassTask(@NonNull InExaminer inExaminer) {
        log.info("oID{},eID{},pM{},tN{}", inExaminer.getOperatorID(), inExaminer.getExaminerID(), inExaminer.getProdModel(), inExaminer.getTaskName());
        if (inExaminer.getExaminStatus() > 0 && inExaminer.getExaminStatus() < 5) {
            Task task = operatorMapper.selectTask(inExaminer.getProdModel(), inExaminer.getTaskName());
            /*合格时进行下一任务，不合格继续当前任务为现任务*/
            if (task != null && inExaminer.getExaminStatus() == 2) {

                examinerMapper.updateTaskExaminStatus(inExaminer.getExaminerID(), inExaminer.getProdModel()
                        , inExaminer.getTaskName(), inExaminer.getExaminStatus(), 3);
                /** 把当前任务修改为 下一个顺序任务
                 *
                *招到当前任务顺序号，；
                * 依据当前任务顺序号+1 修改下一任务为当前任务*/

                ProdTaskUserTo prodTaskUserTo = oprLeaderMapper.selectNextTaskNum(inExaminer.getProdModel(), inExaminer.getTaskName());
                String ptuTaskName = oprLeaderMapper.selectNextTaskName(prodTaskUserTo.getProdId(), (prodTaskUserTo.getTaskOrder()+1));
                oprLeaderMapper.updateNextNowTask(inExaminer.getProdModel(),ptuTaskName);
                /*--------hprose注释了---------*/
//                pushDataMain.creatAndPushData(inExaminer.getProdModel(),inExaminer.getTaskName());
                return new RespondDto(ResultCode.OK, "检验员 检验任务完成");
            } else  if(task != null && inExaminer.getExaminStatus() == 3){

                examinerMapper.updateTaskExaminStatus(inExaminer.getExaminerID(), inExaminer.getProdModel()
                        , inExaminer.getTaskName(), inExaminer.getExaminStatus(), 3);
                /** 把当前任务修改为 下一个顺序任务
                 *
                 *招到当前任务顺序号，；
                 * 依据当前任务顺序号+1 修改下一任务为当前任务*/

                ProdTaskUserTo prodTaskUserTo = oprLeaderMapper.selectNextTaskNum(inExaminer.getProdModel(), inExaminer.getTaskName());
                String ptuTaskName = oprLeaderMapper.selectNextTaskName(prodTaskUserTo.getProdId(), (prodTaskUserTo.getTaskOrder()));
                oprLeaderMapper.updateNextNowTask(inExaminer.getProdModel(),ptuTaskName);
                /*--------hprose注释了---------*/
//                pushDataMain.creatAndPushData(inExaminer.getProdModel(),inExaminer.getTaskName());
                return new RespondDto(ResultCode.OK, "检验员 检验任务完成");
            }else {
                return new RespondDto(ResultCode.NO, "未找到对应任务");
            }
        } else {
            return new RespondDto(ResultCode.NO, "传入检验状态不正确");
        }

    }
}
