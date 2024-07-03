package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

import java.io.Serializable;

/**planAdmin 计划管理员产品任务列表 分页获取产品列表与任务列表>>prodListDetail>>children
 * @author GuoYanSong
 * @Date 2023-4-14*/
@Data
public class PlanCardsChildrenTvo implements Serializable {

    private String taskName;
    private String operator;
    private Integer planDay;
    private Integer realDay;
    private Integer taskStatus;
    private String taskCreatDay;

    private Integer id;

}
