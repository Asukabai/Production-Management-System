package com.ss.managesys.controller;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InExaminer;
import com.ss.managesys.service.ExaminerService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**第三方检验员对任务的操作
 * @author GuoYanSong
 * @Date 2023-4-13*/
@ApiResult
@RequestMapping("/managesys/server/examiner")
@Slf4j
public class ExaminerController {
    @Autowired
    private ExaminerService examinerService;

    /**【第三方检验员任务单列表】
     * 192.168.1.26:20234/managesys/server/examiner/show_exa_task
     * */
    @PostMapping("/show_exa_task")
    public RespondDto showExaminerTaskList(@RequestBody InExaminer inExaminer){
//        log.info("【第三方检验员任务单列表】{}",inExaminer);
//        log.info("getKeywords{}==getCurrentPage{}==getPageSize{}===getExaminerID{}",
//                inExaminer.getKeywords(),inExaminer.getCurrentPage(),
//                inExaminer.getPageSize(),inExaminer.getExaminerID());
        return examinerService.showAllExaminerTask(inExaminer);
    }

    /**【第三方检验员判断任务是否合格】
     * 192.168.1.26:20234/managesys/server/examiner/pass_status
     * */
    @PostMapping("/pass_status")
    public RespondDto ifPassTaskStatus(@RequestBody InExaminer inExaminer){
        log.info("【第三方检验员判断任务是否合格】{}",inExaminer);
        return examinerService.ifPassTask(inExaminer);
    }

    /**【第三方检验员出具检验单】
     * 192.168.1.26:20234/managesys/server/examiner/check_card
     * */
    @PostMapping("/check_card")
    public RespondDto showCheckedCard(@RequestBody InExaminer inExaminer){
        log.info("【第三方检验员出具检验单】{}",inExaminer);
        return examinerService.creatCheckedCard(inExaminer);
    }
}
