package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

import java.io.Serializable;

/**operator 检验员任务列表 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-14*/
@Data
public class ExaminerTvo implements Serializable {
    private String prodModel;
    private String taskName;
    private String operator;
    private String operatorIds;
    private Integer examinStatus;
}
