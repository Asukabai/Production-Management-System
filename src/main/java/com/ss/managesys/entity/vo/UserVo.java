package com.ss.managesys.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**User响应体
 * @author GuoYanSong
 * @Date 2023-4-11*/
@Data
public class UserVo implements Serializable {
    @TableId(value = "user_id",type = IdType.AUTO)
    private String auth;
    private Integer userId;
    private String userName;

}
