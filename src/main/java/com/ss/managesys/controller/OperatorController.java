package com.ss.managesys.controller;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InOperator;
import com.ss.managesys.service.OperatorService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**操作员对任务的操作
 * @author GuoYanSong
 * @Date 2023-4-12*/
@ApiResult
@RequestMapping("/managesys/server/operator")
@Slf4j
public class OperatorController {

    @Autowired
    private OperatorService operatorService;

    /**
     * 【操作员任务列表】 展示该操作员所有任务
     * 192.168.1.26:20234/managesys/server/operator/show_all_task
     * */
    @PostMapping("/show_all_task")
    public RespondDto showAllTask(@RequestBody InOperator inOperator){
//        log.info("【操作员任务列表】{}",inOperator);
        return operatorService.showAllStack(inOperator);
    }

    /**【操作员提交任务】
     * 192.168.1.26:20234/managesys/server/operator/sub_task
     * */
    @PostMapping("/sub_task")
    public RespondDto subTask(@RequestBody InOperator inOperator){
        log.info("【操作员提交任务】{}",inOperator);
        return operatorService.subOperaTask(inOperator);
    }

}
