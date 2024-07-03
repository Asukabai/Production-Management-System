package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InSuperAdmin;

/**
 * 超级管理员操作
 *
 * @author GuoYanSong
 * @Date 2023-4-11
 */
public interface SuperAdminService {

    /**
     * 展示所有用户
     */
    RespondDto showAllUsers(InSuperAdmin inSuperAdmin);

    /**
     * 【修改用户】依据用户id修改用户权限
     */
    RespondDto updateUserAuth(InSuperAdmin inSuperAdmin);

    /**【删除用户】*/
    RespondDto deleteUser(InSuperAdmin inSuperAdmin);

    /**【新增用户】*/
    RespondDto addUser(InSuperAdmin inSuperAdmin);

    /**【展示全部权限项】*/
    RespondDto showAllAuth();
}
