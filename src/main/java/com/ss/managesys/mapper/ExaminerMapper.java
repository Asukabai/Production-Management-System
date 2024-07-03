package com.ss.managesys.mapper;

import com.ss.managesys.entity.po.Task;
import com.ss.managesys.entity.vo.InOperator;
import com.ss.managesys.entity.vo.tvo.ExaminerTvo;
import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import com.ss.managesys.entity.vo.tvo.TaskAndProd;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**检验员对任务的操作
 * @author GuoYanSong
 * @Date 2023-4-13*/

@Mapper
public interface ExaminerMapper {

    /**【出具检验单】select task 查任务表全部符合条件的任务*/
    Integer selectTaskList(@Param("ptos") ArrayList<InOperator> ptos);

    /**【任务是否合格】修改检验状态*/
    Integer updateTaskExaminStatus(Integer examinerId,String prodMode,String taskName,Integer examinStatus,Integer taskStatus);

    /**【第三方检验员出具检验单】*/
    Integer updateTaskCheckCard(@Param("ptos") ArrayList<InOperator> ptos,Integer examinerId);

    /**【检验员产品任务列表】补充任务列表其他字段 */
    ArrayList<ExaminerTvo> selectTaskExamCards(@Param("taskAndProds") ArrayList<TaskAndProd> taskAndProds);
    /**【检验员产品任务列表】补充任务对应操作员 */
    ArrayList<ExaminerTvo> selectTaskOperators (@Param("taskAndProds") ArrayList<TaskAndProd> taskAndProds,Integer authOperator);
}
