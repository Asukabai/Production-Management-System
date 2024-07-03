package com.ss.managesys.mapper;

import com.ss.managesys.entity.po.Task;
import com.ss.managesys.entity.to.ProdTaskUserTo;
import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import com.ss.managesys.entity.vo.tvo.TaskAndProd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**操作员对任务的操作
 * @author GuoYanSong
 * @Date 2023-4-12*/

@Mapper
public interface OperatorMapper {
//
//    Integer insertptu(Integer prodId, Integer taskName, Integer memberId, Integer taskOrder, Integer leaderId);
//    ProdTaskUserTo selectProdIdAndTaskOrder(Integer prodModel, Integer taskName);




    /**【操作员产品任务列表】》》查出产品型号和任务名称含有关键字的任务 名称*/
    ArrayList<String> selectTaskName(Integer operatorId,Integer authOperator,String keywords);
    /**【任务列表】select task 查任务和对应的产品*/
    ArrayList<TaskAndProd> selectTaskAndProd(Integer operatorId,Integer authOperator,String keywords);

    /**【操作员产品任务列表】补充任务列表其他字段 */
    ArrayList<OperatorTvo> selectTaskOperCards(@Param("taskAndProds") ArrayList<TaskAndProd> taskAndProds);

    /**【操作员产品任务列表】补充任务对应检验员 */
    ArrayList<OperatorTvo> selectTaskExaminer(@Param("taskAndProds") ArrayList<TaskAndProd> taskAndProds,Integer authExaminer);

    /**【提交任务】select task 查任务表单个任务*/
    Task selectTask(String prodMode,String taskName);


    /**【提交任务】修改对应的字段内容*/
    /*修改 移交任务 检验员  一个变成多个 2023-4-17
     *   1、不用再设置传入检验员为实际检验员；*/
//    Integer updateTaskOperatorSend(Integer operatorId,Integer examinerId,String prodMode,String taskName);
    Integer updateTaskOperatorSend(Integer operatorId,String prodMode,String taskName);

}
