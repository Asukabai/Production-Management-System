package com.ss.managesys.controller;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InOperator;
import com.ss.managesys.entity.vo.InOprLeader;
import com.ss.managesys.service.OperatorService;
import com.ss.managesys.service.OprLeaderService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**操作员对任务的操作
 * @author GuoYanSong
 * @Date 2023-4-20*/
@ApiResult
@RequestMapping("/managesys/server")
@Slf4j
public class OprLeaderController {

    @Autowired
    private OprLeaderService oprLeaderService;

    /**
     * 【操作员组长】 展示组员姓名
     *  192.168.1.26:20234/managesys/server/oleader/show_oprlists
     * */
    @PostMapping("/oleader/show_oprlists")
    public RespondDto showAllTask(@RequestBody InOprLeader inOprLeader){
        return oprLeaderService.showAllMember(inOprLeader);
    }

    /**【操作员组长】 派发任务
     192.168.1.26:20234/managesys/server/oleader/send_task
     */
     @PostMapping("/oleader/send_task")
    public RespondDto sendTask(@RequestBody InOprLeader inOprLeader){
         log.info("【操作员组长】 派发任务{}",inOprLeader);
        return oprLeaderService.sendTask(inOprLeader);
    }


    /**【第三方检验员终止任务】
     * 192.168.1.26:20234/managesys/server/examiner/stop_task
     * */
    @PostMapping("/examiner/stop_task")
    public RespondDto examinerStopTask(@RequestBody InOprLeader inOprLeader){
        log.info("【第三方检验员终止任务】{}",inOprLeader);
        return oprLeaderService.examinerStopTask(inOprLeader);
    }
}
