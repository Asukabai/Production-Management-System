package com.ss.managesys.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**product 实体类
 * @author GuoYanSong
 * @Date 2023-4-18*/
@Data
@TableName("product")
public class Product {
    @TableId(value = "prod_id",type = IdType.AUTO)
    private Integer prodId;

    private String prodModel;
    private String prodStage;
    private String prodDirector;
    @JsonProperty("prodStatus")
    private Integer prodStatus;

    private String taskCreatday;
    private String taskUpdateday;
    private Integer ifsend;
    private Integer creatorId;
    private Integer prodOrder;
    private Integer ifdelete;

    private String prodStatusString;
}
