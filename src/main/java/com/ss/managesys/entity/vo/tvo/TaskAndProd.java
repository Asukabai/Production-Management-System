package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

import java.io.Serializable;

/** 操作员   任务和对应产品信息
 * @author GuoYanSong
 * @Date 2023-4-18*/
@Data
public class TaskAndProd implements Serializable {
    private String TaskName;
    private Integer prodId;
    private String prodModel;
}
