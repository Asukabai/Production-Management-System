package com.ss.managesys.controller;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InSuperAdmin;
import com.ss.managesys.service.SuperAdminService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**超级管理员对员工的crud
 * @author GuoYanSong
 * @Date 2023-4-12*/
@ApiResult
@RequestMapping("/managesys/server/user")
@Slf4j
public class SuperAdminController {
    @Autowired
    private SuperAdminService superAdminService;

    /**【用户列表】 展示所有用户
     * 192.168.1.26:20234/managesys/server/user/show_users
     * */
    @PostMapping("/show_users")
    public RespondDto showAllUsers(@RequestBody InSuperAdmin inSuperAdmin){
        log.info("【用户列表】{}",inSuperAdmin);
        return superAdminService.showAllUsers(inSuperAdmin);
    }

    /**【修改用户】依据用户id修改用户权限
     * 192.168.1.26:20234/managesys/server/user/change_user
     * */
    @PostMapping("/change_user")
    public RespondDto changeUserAuth(@RequestBody InSuperAdmin inSuperAdmin){
        log.info("【修改用户】{}",inSuperAdmin);
        return superAdminService.updateUserAuth(inSuperAdmin);
    }

    /**【删除用户】依据用户id删除用户
     * 192.168.1.26:20234/managesys/server/user/remove_user
     * */
    @PostMapping("/remove_user")
    public RespondDto removeUser(@RequestBody InSuperAdmin inSuperAdmin){
        log.info("【删除用户】{}",inSuperAdmin);
        return superAdminService.deleteUser(inSuperAdmin);
    }

    /**【新增用户】
     * 192.168.1.26:20234/managesys/server/user/new_user
     * */
    @PostMapping("/new_user")
    public RespondDto newUser(@RequestBody InSuperAdmin inSuperAdmin){
        log.info("【新增用户】{}",inSuperAdmin);
        return superAdminService.addUser(inSuperAdmin);
    }

    /**【展示全部权限项】
     * 192.168.1.26:20234/managesys/server/user/show_auth
     * */
    @PostMapping("/show_auth")
    public RespondDto newAuth(){
        log.info("【展示全部权限项】{}");
        return superAdminService.showAllAuth();
    }
}
