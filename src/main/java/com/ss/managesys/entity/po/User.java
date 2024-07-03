package com.ss.managesys.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**User实体类
 * @author GuoYanSong
 * @Date 2023-4-11*/
@Data
@TableName("user")
public class User {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;

    private String userName;
    private String userPassword;
    private Integer auth;
    private String nickName;
    private String tel;
}
