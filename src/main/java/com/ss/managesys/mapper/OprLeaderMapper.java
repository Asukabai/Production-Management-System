package com.ss.managesys.mapper;


import com.ss.managesys.entity.to.ProdTaskUserTo;
import com.ss.managesys.entity.vo.OprLeaderVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**操作员 组长
 * @author GuoYanSong
 * @Date 2023-4-20*/

@Mapper
public interface OprLeaderMapper {


//    @Select("SELECT user_id AS value,user_name AS label FROM user WHERE auth = 5 AND ifdelete = 0")
    List<OprLeaderVo> showAllmemberIdAndName();

//    @Insert("INSERT INTO prod_task_user (prod_id,task_name,user_id,user_auth,task_order,teamleaderId)VALUES"+
////            " (#{prodId},#{taskName},#{memberId},5,#{taskOrder},#{leaderId}")
    Integer insertptu(Integer prodId, String taskName, Integer memberId, Integer taskOrder, Integer leaderId);
//
//
//    /**【操作员组长】 派发任务 找出任务的id 和 task_order*/
////    @Select("SELECT t1.prod_id,t2.task_order FROM product AS t1" +
////            "LEFT JOIN prod_task_user AS t2 ON t1.prod_id = t2.prod_id" +
////            "WHERE　t1.ifdelete = 0 AND t2.task_name = #{taskName} AND t1.prod_model = #{prodModel}")
    ProdTaskUserTo selectProdIdAndTaskOrder(String prodModel, String taskName,Integer leaderId);

    @Update("UPDATE task SET task_status = 6 WHERE ifdelete = 0 AND prod_model = #{prodModel} AND " +
            "task_name = #{taskName}")
    Integer updateTaskStatus(String prodModel,String taskName);

    @Update("UPDATE product SET prod_status = 5 WHERE ifdelete = 0 AND prod_model = #{prodModel}")
    Integer updateProductStatus(String prodModel);


    /**查询当前操作员是不是组长*/
    @Select("SELECT auth FROM user WHERE user_name = #{oprleaderName} AND ifdelete = 0")
    Integer selectUserAuthByName (String oprleaderName);

    /**新增产品和任务时修改序列为1的任务为当前任务*/
    @Update("UPDATE task SET if_now_task = 1 WHERE prod_model = #{prodModel} AND task_name = #{taskName}")
    Integer updateTaskNow(String prodModel, String taskName);


    /**找出下一个任务 序列号 */
    @Select("SELECT t1.prod_id,t1.task_order FROM prod_task_user AS t1 LEFT JOIN product AS t2 ON t1.prod_id = t2.prod_id " +
            "WHERE t1.task_name = #{taskName} AND t2.prod_model = #{prodModel} AND t2.ifdelete = 0 LIMIT 1")
    ProdTaskUserTo selectNextTaskNum(String prodModel, String taskName);
    /**找出下一个任务 名 */
    @Select("SELECT task_name FROM prod_task_user WHERE prod_id = #{prodId} AND task_order = #{taskOrder} LIMIT 1")
    String selectNextTaskName(Integer prodId, Integer taskOrder);

    /**修改下一个序列号任务为当前任务 */
    @Update("UPDATE task SET if_now_task = 1 " +
            "WHERE prod_model = #{prodModel} AND task_name = #{taskName}")
    Integer updateNextNowTask(String prodModel, String taskName);
}
