package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**planAdmin 计划管理员产品任务列表 分页获取产品列表与任务列表>>prodListDetail
 * @author GuoYanSong
 * @Date 2023-4-14*/
@Data
public class PlanCardsProdListDetailTvo implements Serializable {
    private Integer prodId;
    private String prodModel;
    private String prodStage;
    private Integer prodStatus;
    private String prodCreateDay;
    private String ifsend;
    private List<PlanCardsChildrenTvo> children;

    private Integer id;
}
