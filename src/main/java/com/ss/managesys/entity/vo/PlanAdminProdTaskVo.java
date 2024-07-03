package com.ss.managesys.entity.vo;

import com.ss.managesys.entity.vo.tvo.PlanCardsProdListDetailTvo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**计划管理员查看任务检验单 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-16*/
@Data
@AllArgsConstructor
public class PlanAdminProdTaskVo implements Serializable {
    private Integer totalDataNum;
    private List<PlanCardsProdListDetailTvo> prodListDetail;

}
