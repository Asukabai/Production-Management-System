package com.ss.managesys.entity.vo;

import com.ss.managesys.entity.vo.tvo.ExaminerTvo;
import com.ss.managesys.entity.vo.tvo.OperatorTvo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**检验员查看任务检验单 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-14*/
@Data
@AllArgsConstructor
public class ExaminerVo implements Serializable {
    private Integer totalDataNum;
    private List<ExaminerTvo> dataDetail;
}
