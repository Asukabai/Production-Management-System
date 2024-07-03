package com.ss.managesys.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

/**超级管理员展示用户列表 返回值消息体
 * @author GuoYanSong
 * @Date 2023-4-14*/
@Data
public class SuperAdminVo implements Serializable {
    private Integer totalDataNum;
    private ArrayList<UserVo> dataDetail;
}
