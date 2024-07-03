package com.ss.managesys.controller;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InPlanAdmin;
import com.ss.managesys.service.PlanAdminService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**计划管理员对产品和任务的操作
 * @author GuoYanSong
 * @Date 2023-4-12*/
@ApiResult
@RequestMapping("/managesys/server/plan")
@Slf4j
public class PlanAdminController {
    @Autowired
    private PlanAdminService planAdminService;

    /**
     * 【计划管理员创建新产品】
     * 192.168.1.26:20234/managesys/server/plan/prod/new_prod
     * */

    @PostMapping("/prod/new_prod")
    public RespondDto planNewProd(@RequestParam("prodModel") String prodModel,
                                  @RequestParam("prodStage") String prodStage,
                                  @RequestParam("prodDirector") String prodDirector,
                                  @RequestParam("creatorID") Integer creatorID,
                                  @RequestParam("file") MultipartFile multipartFile){
//        log.info("【查看任务检验单】  file:{} ;prodModel:{};prodStage:{};prodDirector:{};creatorID:{};",
//                multipartFile.getName(),prodModel,prodStage,prodDirector,creatorID);
        return planAdminService.panNewProd(prodModel,prodStage,prodDirector,creatorID,multipartFile);
    }

    /**
     * 【计划管理员分页获取产品列表与任务列表】
     * 192.168.1.26:20234/managesys/server/plan/prod/prod_task_lists
     * */
    @PostMapping("/prod/prod_task_lists")
    public RespondDto showProdAndStackLists(@RequestBody InPlanAdmin inPlanAdmin){
//        log.info("【计划管理员分页获取产品列表与任务列表】{}",inPlanAdmin);
        return planAdminService.showProdAndTaskLists(inPlanAdmin);
    }

    /**【查看任务检验单】
     * 192.168.1.26:20234/managesys/server/plan/stack/check_card
     * */
    @PostMapping("/stack/check_card")
    public RespondDto showTaskCheckCard(@RequestBody InPlanAdmin inPlanAdmin){
        log.info("【查看任务检验单】{}",inPlanAdmin);
        return planAdminService.showCheckCard(inPlanAdmin);
    }

    /**【删除产品】依据用户id删除产品
     * 192.168.1.26:20234/managesys/server/plan/prod/remove_prod
     * */
    @PostMapping("/prod/remove_prod")
    public RespondDto removeProd(@RequestBody InPlanAdmin inPlanAdmin){
        log.info("【删除产品】{}",inPlanAdmin);
        return planAdminService.deleteProd(inPlanAdmin);
    }

    /**【下发产品任务】依据用户id下发
     * 192.168.1.26:20234/managesys/server/plan/prod/send_prod
     * */
    @PostMapping("/prod/send_prod")
    public RespondDto sendProd(@RequestBody InPlanAdmin inPlanAdmin){
        log.info("【下发产品任务】{}",inPlanAdmin);
        return planAdminService.updateProdSend(inPlanAdmin);
    }
}
