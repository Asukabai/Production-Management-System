package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**login返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Data
public class LoginVo implements Serializable {
    private String token;
    private Integer auth;
    private Integer userID;
    private String uN;
}
