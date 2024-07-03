package com.ss.managesys.entity.vo.tvo;

import lombok.Data;

import java.io.Serializable;

/**planAdmin 计划管理员新增产品 接受excel文件内容
 * @author GuoYanSong
 * @Date 2023-4-16*/
@Data
public class PlanExcelTvo implements Serializable {

    private String taskName;
    private String operator;
    private Integer planDay;
    private String examiner;

    private Integer orderNum;
}
