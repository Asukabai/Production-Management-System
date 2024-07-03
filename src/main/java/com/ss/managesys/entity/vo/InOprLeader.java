package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**接收 操作员组长 入参
 * @author GuoYanSong
 * @Date 2023-4-20*/
@Data
public class InOprLeader implements Serializable {
    private Integer leaderId;
    private Integer memberId;
    private String taskName;
    private String prodModel;
}
