package com.ss.managesys.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**operator 操作员组长 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-20*/
@Data
@AllArgsConstructor
public class OprLeaderVo implements Serializable {
    private Integer value;
    private String label;
}
