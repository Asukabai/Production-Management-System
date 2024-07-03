package com.ss.managesys.entity.vo;

import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**operator 操作员产品任务列表 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
@AllArgsConstructor
public class OperatorVo implements Serializable {
    private Integer totalDataNum;
    private List<OperatorTvo> dataDetail;
}
