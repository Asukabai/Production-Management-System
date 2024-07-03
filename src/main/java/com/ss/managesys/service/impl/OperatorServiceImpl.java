package com.ss.managesys.service.impl;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.po.Task;
import com.ss.managesys.entity.to.ProdTaskUserTo;
import com.ss.managesys.entity.vo.InOperator;
import com.ss.managesys.entity.vo.InOprLeader;
import com.ss.managesys.entity.vo.OperatorVo;
import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import com.ss.managesys.entity.vo.tvo.TaskAndProd;
import com.ss.managesys.mapper.OperatorMapper;
import com.ss.managesys.mapper.PushDataMapper;
import com.ss.managesys.push_data.PushDataMain;
import com.ss.managesys.push_data.PushProductMGNData;
import com.ss.managesys.push_data.PushTaskMGNData;
import com.ss.managesys.service.OperatorService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

import java.util.List;

/**
 * 操作员对产品任务的操作
 *
 * @author GuoYanSong
 * @Date 2023-4-12
 */
@Service
public class OperatorServiceImpl implements OperatorService {

    @Value("${user.authOperator}")
    private Integer authOperator;
    @Value("${user.authExaminer}")
    private Integer authExaminer;


    @Resource
    private OperatorMapper operatorMapper;
    @Resource
    private PushDataMapper pushDataMapper;
    @Resource
    private PushDataMain pushDataMain;




    /**操作员提交任务
     * 1、判断任务是否被提交 if_Operator_sub == 0；
     * 2、为0 未提交 进行 修改，同时将操作员检验员设为实际操作、检验员；*/
    @Override
    public RespondDto subOperaTask(@NonNull InOperator inOperator) {
        if(inOperator.getOperatorID() != null && inOperator.getExaminerIds() != null){
            Task task = operatorMapper.selectTask(inOperator.getProdModel(), inOperator.getTaskName());
            if(task != null){
                if(task.getIfOperatorSub() == 0 && task.getIfNowTask() == 1){
                    /*修改 移交任务 检验员  一个变成多个 2023-4-17
                    *   1、不用再设置传入检验员为实际检验员；*/
//                    operatorMapper.updateTaskOperatorSend(inOperator.getOperatorID(), inOperator.getExaminerID(), inOperator.getProdModel(), inOperator.getTaskName());
                    operatorMapper.updateTaskOperatorSend(inOperator.getOperatorID(),inOperator.getProdModel(), inOperator.getTaskName());

                    /*--------hprose注释了---------*/
//                    PushProductMGNData pushProductMGNData = pushDataMapper.selectPushProductMGNData(inOperator.getProdModel());
//                    ArrayList<PushTaskMGNData> pushTaskMGNData = new ArrayList<>();
//                    pushTaskMGNData.add(new PushTaskMGNData(inOperator.getTaskName(),"检验中"));
//                    pushProductMGNData.setTask(pushTaskMGNData);
//                    pushDataMain.hprosePush(pushProductMGNData);

                    return new RespondDto(ResultCode.OK,"操作员任务提交成功");
                }else if(task.getIfOperatorSub() == 1 && task.getExaminStatus() == 3 && task.getIfNowTask() == 1){
                    operatorMapper.updateTaskOperatorSend(inOperator.getOperatorID(),inOperator.getProdModel(), inOperator.getTaskName());

                    /*--------hprose注释了---------*/
//                    PushProductMGNData pushProductMGNData = pushDataMapper.selectPushProductMGNData(inOperator.getProdModel());
//                    ArrayList<PushTaskMGNData> pushTaskMGNData = new ArrayList<>();
//                    pushTaskMGNData.add(new PushTaskMGNData(inOperator.getTaskName(),"检验中"));
//                    pushProductMGNData.setTask(pushTaskMGNData);
//                    pushDataMain.hprosePush(pushProductMGNData);
                    return new RespondDto(ResultCode.OK,"任务已提交，重复提交");
                }else if(task.getIfNowTask() == 0){
                    return new RespondDto(ResultCode.NO,"不可提交！前置任务未合格");
                }else {
                    return new RespondDto(ResultCode.NO,"操作员任务提交失败");
                }
            }else {
                return new RespondDto(ResultCode.NO,"没有找到相匹配的任务");
            }
        }else {
            return new RespondDto(ResultCode.NO,"入参操作员或检验员错误");
        }
    }

    /**操作员产品任务列表
     * 1、在 prod 表查出已下发的产品主键id prod_id；
     * 2、在prod_task_user表找出 已下发的、操作员的、对应的任务名称；
     * 3、把该任务列表作为条件 foreach 在 ptu 补充检验员在  task 表补齐字段
     *              a、删除有空
     *
     *              分页：数据总条数 totalDataNum；
     *                   当前页码数  currentPage
     *                   每页展示条数 pageSize
     *
     *                   筛选出当前的任务 task -》 if_now_task*/
    @Override
    public RespondDto showAllStack(@NonNull InOperator inOperator) {

//        拼sql时防止拼null
        if(inOperator.getCurrentPage() == null || inOperator.getPageSize() == null){
            return new RespondDto(ResultCode.NO,"请检查是否成功传入CurrentPage和PageSize");
        }
        if(inOperator.getKeywords() == null){
            inOperator.setKeywords("");
        }
//        ArrayList<OperatorVo> operatorVos = new ArrayList<>();
        ArrayList<TaskAndProd> taskNameAndProds = operatorMapper.selectTaskAndProd(inOperator.getOperatorID(),authOperator,inOperator.getKeywords());
//        System.out.println("查到的任务名称：taskNames:"+taskNameAndProds);


//        Integer totalDataNum = taskNames.size();  直接返回了数组的大小
        Integer limitIndex = (inOperator.getCurrentPage()-1) * inOperator.getPageSize();
        Integer limitEnd = inOperator.getCurrentPage() * inOperator.getPageSize();

//        System.out.println("符合条件的任务数量"+taskNameAndProds.size());


        if(taskNameAndProds.size() > 0){
            ArrayList<OperatorTvo> operatorTvos = operatorMapper.selectTaskOperCards(taskNameAndProds);
            ArrayList<OperatorTvo> examiner = operatorMapper.selectTaskExaminer(taskNameAndProds, authExaminer);

            /*替换task中为检验员为null的项 赋值操作员名和id*/
            if(operatorTvos != null && operatorTvos.size() > 0 && examiner != null&&examiner.size()>0){
                for (int i = 0; i < operatorTvos.size(); i++) {
                    if(operatorTvos.get(i).getExaminerIds() == null){
                        operatorTvos.get(i).setExaminerIds(examiner.get(i).getExaminerIds());
                        operatorTvos.get(i).setExaminer(examiner.get(i).getExaminer());
//                        System.out.println("104 为null 了第"+i+"次" + operatorTvos.get(i).getExaminerIds()
//                                +"======="+examiner.get(i).getExaminerIds());

                    }
                }
            }

            if(limitIndex > operatorTvos.size()){
                List<OperatorTvo> returnOperators = new ArrayList<OperatorTvo>();
                return new RespondDto(ResultCode.NO,"请检查传入起始页数是否正确",new OperatorVo(0,returnOperators));
            }else if(limitEnd >= operatorTvos.size()){
                List<OperatorTvo> returnOperators = operatorTvos.subList(limitIndex, operatorTvos.size());
                return new RespondDto(ResultCode.OK,"操作员产品任务列表查询成功",new OperatorVo(operatorTvos.size(),returnOperators));
            }else if(limitEnd < operatorTvos.size()){
                List<OperatorTvo> returnOperators = operatorTvos.subList(limitIndex, limitEnd);
                return new RespondDto(ResultCode.OK,"操作员产品任务列表查询成功",new OperatorVo(operatorTvos.size(),returnOperators));
            }else {
                List<OperatorTvo> returnOperators = new ArrayList<OperatorTvo>();
                return new RespondDto(ResultCode.NO,"分页搜索展示失败",new OperatorVo(0,returnOperators));
            }

//            Iterator<OperatorTvo> iterator = operatorTvos.iterator();
//            while (iterator.hasNext()){
//                if(iterator.next().getExaminerID() == null){
//
//                    iterator.next().setExaminerID();
//                }
//            }



//            for (OperatorTvo operatorTvo : operatorTvos) {
//                /*selectTaskOperCards 找到的检验员是实际操作检验员，如果任务还没被提交过就会没有实际操作检验员
//                * 所以判断没有实际操作检验员时进行额外的查询补充*/
//                if(operatorTvo.getExaminerID() == null){
//                    ArrayList<OperatorTvo> examinerNameAndIds = operatorMapper.selectTaskExaminer(operatorTvo.getTaskName(), authExaminer);
//                    if(examinerNameAndIds.size() == 1){
//                        operatorTvo.setExaminer(examinerNameAndIds.get(0).getExaminer());
//                        operatorTvo.setExaminerID(examinerNameAndIds.get(0).getExaminerID());
//                    }
//                    else if (examinerNameAndIds.size() > 1){
//                        OperatorTvo tempOperator = operatorTvo;
////                        int point = operatorTvos.indexOf(operatorTvo);
//                        for (OperatorTvo examinerNameAndId : examinerNameAndIds) {
//                            tempOperator.setExaminer(examinerNameAndId.getExaminer());
//                            tempOperator.setExaminerID(examinerNameAndId.getExaminerID());
//                            operatorTvos.add(tempOperator);
//                        }
////                        operatorTvos.remove(operatorTvo);
//                    }
//                }
//            }
//            return new RespondDto(ResultCode.OK,"操作员产品任务列表查询成功",new OperatorVo(totalDataNum,operatorTvos));
        }else {
            List<OperatorTvo> returnOperators = new ArrayList<OperatorTvo>();;
            return new RespondDto(ResultCode.OK,"该操作员暂时没有产品操作任务",new OperatorVo(0,returnOperators));
        }

        /*    <!--【操作员产品任务列表】补充任务对应检验员
            查找的为对应匹配的 没有被检验员确认的 多个 检验员情况-->*/
//        ArrayList<OperatorTvo> operatorTvos = operatorMapper.selectTaskExaminer(taskNames,authExaminer);
//        System.out.println(operatorTvos);

    }
}
