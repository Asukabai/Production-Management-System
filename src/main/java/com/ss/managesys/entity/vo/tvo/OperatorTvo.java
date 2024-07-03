package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

/**operator 操作员产品任务列表 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class OperatorTvo {
    private String prodModel;
    private String taskName;
    private Integer planDay;
    private Integer realDay;
    private Integer taskStatus;
    private String examiner;

    private String examinerIds;
    private Integer examinStatus;
    private String taskCreatDay;
}
