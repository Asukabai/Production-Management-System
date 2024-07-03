package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InPlanAdmin;
import org.springframework.web.multipart.MultipartFile;

/**
 * 计划管理员操作
 *
 * @author GuoYanSong
 * @Date 2023-4-11
 */
public interface PlanAdminService {


    /**【删除产品】*/
    RespondDto deleteProd(InPlanAdmin inPlanAdmin);

    /**【下发产品任务】*/
    RespondDto updateProdSend(InPlanAdmin inPlanAdmin);

    /**【查看任务检验单】*/
    RespondDto showCheckCard(InPlanAdmin inPlanAdmin);

    /**【计划管理员分页获取产品列表与任务列表】*/
    RespondDto showProdAndTaskLists(InPlanAdmin inPlanAdmin);

    /**【计划管理员创建新产品】*/
    RespondDto panNewProd(String prodModel, String prodStage, String prodDirector, Integer creatorID, MultipartFile multipartFile);
}
