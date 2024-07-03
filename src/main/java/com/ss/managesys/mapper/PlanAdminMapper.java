package com.ss.managesys.mapper;

import com.ss.managesys.entity.po.ProdTaskUser;
import com.ss.managesys.entity.po.Product;
import com.ss.managesys.entity.vo.PlanAdminCheckCardVo;
import com.ss.managesys.entity.vo.tvo.ExaminerTvo;
import com.ss.managesys.entity.vo.tvo.PlanCardsChildrenTvo;
import com.ss.managesys.entity.vo.tvo.PlanCardsProdListDetailTvo;
import com.ss.managesys.entity.vo.tvo.PlanExcelTvo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**计划管理员对任务和产品 操作
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Mapper
public interface PlanAdminMapper {

    /**【根据人名找id】*/
    Integer selectUserIDByUserName (String userName);

    /**【计划管理员分页获取产品列表与任务列表】 查出需要展示得到产品*/
    ArrayList<PlanCardsProdListDetailTvo> selectProd();
    /**【计划管理员分页获取产品列表与任务列表】 查出产品对应的任务*/
    ArrayList<PlanCardsChildrenTvo> selectTask(String prodModel,String keywords);
    /**【检验员产品任务列表】补充任务对应操作员 */
    ExaminerTvo selectTaskOperators (String taskName,Integer prodId, Integer authOperator);

    /**依据用户 id 删除产品**/
    Integer updateProdDelete(String prodModel);

    /**依据用户 id 下发产品任务 修改 产品状态**/
    Integer updateProdSendAndStatus(String prodModel,Integer statusShiShi);
    /*检验任务是否已被下发*/
    Product selectProdIfSend (String prodModel);

    /**【查看任务检验单】*/
    ArrayList<PlanAdminCheckCardVo> selectCheckCard(String prodModel);

    /**【上传新产品任务】 保存 prod 产品*/
    Integer insertProd(String prodModel, String prodStage, String prodDirector, Integer creatorID);
    /**【上传新产品任务】 保存 task 任务*/
    Integer insertTask(String prodModel,@Param("planExcelTvos") ArrayList<PlanExcelTvo> planExcelTvos);
    /**【上传新产品任务】 查，准备需要insert的数据 操作员和检验员 */
    ArrayList<ProdTaskUser> selectProdTaskUser(String prodModel,@Param("operatorAndExaminers") ArrayList<String> operatorAndExaminers, Integer auth);

    /**【上传新产品任务】 保存 操作员和检验员 */
    Integer insertptoOPrEx(Integer prodId, String taskName, Integer userId, Integer auth, Integer orderNum);
    /**【查找新增产品型号id】*/
    Integer selectProdInsertProdAutoId(String prodModel);
    /**查 看是否已经存在该产品型号*/
    Integer selectIfHave(String prodModel);

//    /**【上传新产品任务】 保存 操作员和检验员 */
//    Integer insertProdTaskUser(String prodModel,String taskName,
//                               @Param("operatorAndExaminers") ArrayList<String> operatorAndExaminers,Integer auth);
}
