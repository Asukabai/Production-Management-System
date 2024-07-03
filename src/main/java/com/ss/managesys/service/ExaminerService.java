package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InExaminer;

/**第三方检验员对任务操作
 * @author GuoYanSong
 * @Date 2023-4-13*/
public interface ExaminerService {

    /**【第三方检验员判断任务是否合格】*/
    RespondDto ifPassTask(InExaminer inExaminer);

    /**【第三方检验员出具检验单】*/
    RespondDto creatCheckedCard(InExaminer inExaminer);

    /**【第三方检验员任务单列表】*/
    RespondDto showAllExaminerTask(InExaminer inExaminer);
}
