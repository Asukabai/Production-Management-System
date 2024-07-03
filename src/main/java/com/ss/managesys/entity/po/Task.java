package com.ss.managesys.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**Task 实体类
 * @author GuoYanSong
 * @Date 2023-4-13*/
@Data
@TableName("task")
public class Task {
    @TableId(value = "task_id",type = IdType.AUTO)
    private Integer taskId;

    private Integer prodId;
    private String prodModel;
    private String taskName;
    private Integer planday;
    private Integer realday;
    private Integer taskStatus;
    private Timestamp taskCreatday;
    private Timestamp taskUpdateday;
    private Integer operatorId;
    private Integer ifOperatorSub;
    private Integer examinerId;
    private Integer examinStatus;
    private Integer ifNowTask;
    private Integer ifCheckCard;
    private Integer ifDelete;
}
