package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InOprLeader;

/**
 * 操作员组长
 *
 * @author GuoYanSong
 * @Date 2023-4-20
 */
public interface OprLeaderService {

    /**【组员列表】*/
    RespondDto showAllMember(InOprLeader inOprLeader);

    /**  【操作员组长】 派发任务*/
    RespondDto sendTask(InOprLeader inOprLeader);

    /**【第三方检验员终止任务】 */
    RespondDto examinerStopTask(InOprLeader inOprLeader);
}
