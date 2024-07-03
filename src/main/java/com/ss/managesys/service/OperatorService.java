package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InOperator;
import com.ss.managesys.entity.vo.InOprLeader;

/**操作员对任务操作
 * @author GuoYanSong
 * @Date 2023-4-12*/
public interface OperatorService {
    /**
     * 展示操作员产品任务列表
     */
    RespondDto showAllStack(InOperator inOperator);

    /**操作员提交任务*/
    RespondDto subOperaTask(InOperator inOperator);

}
