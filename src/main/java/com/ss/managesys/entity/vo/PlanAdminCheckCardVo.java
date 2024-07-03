package com.ss.managesys.entity.vo;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**计划管理员查看任务检验单 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-13*/
@Data
public class PlanAdminCheckCardVo implements Serializable {
    private String prodModel;
    private String prodStage;
    private String taskName;
    private Integer taskStatus;
//    private Timestamp taskCreatday;
    private String taskCreatday;
}
