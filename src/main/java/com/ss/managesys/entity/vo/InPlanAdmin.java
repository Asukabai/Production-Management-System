package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**接收 计划管理员操作任务和产品 入参
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class InPlanAdmin implements Serializable {
//    分页： page 页码；dataNum 每页数据条数
    private Integer currentPage;
    private Integer pageSize;
    private String prodModel;
    private String keywords;
//    任务名称
    private String taskName;
//    产品阶段
    private String prodStage;
//    产品负责人
    private String prodDirector;
//    当前登陆用户ID
    private Integer creatorID;
}
