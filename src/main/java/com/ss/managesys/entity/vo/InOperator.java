package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**接收 操作员相关操作 用入参
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class InOperator implements Serializable {
    /*operatorID: 操作员Id；examinerID: 检验员Id；prodModel: 产品型号；taskName:任务名称； */
    private Integer operatorID;
    private Integer examinerID;

    private String operatorIds;
    private String examinerIds;
    private String prodModel;
    private String taskName;
    private String keywords;
    private Integer currentPage;
    private Integer pageSize;
}
