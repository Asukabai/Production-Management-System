package com.ss.managesys.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**超级管理员展示全部权限项 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
@AllArgsConstructor
public class ShowAuthVo implements Serializable {

    private Integer value;
    private String label;
}
