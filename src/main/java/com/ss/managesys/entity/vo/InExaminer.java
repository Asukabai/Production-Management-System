package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**接收 检验人员相关操作 用入参
 * @author GuoYanSong
 * @Date 2023-4-13*/
@Data
public class InExaminer extends InOperator implements Serializable {
    /*examinStatuss 检验状态 2合格 3不合格*/
    private Integer examinStatus;
//    private Integer examinerID;
    private ArrayList<InOperator> ptos;

}
