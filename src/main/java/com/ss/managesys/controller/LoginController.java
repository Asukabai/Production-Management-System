package com.ss.managesys.controller;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InLogin;
import com.ss.managesys.service.LoginService;
import com.ss.managesys.util.api.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**用户登陆及修改密码
 * @author GuoYanSong
 * @Date 2023-4-12*/
@ApiResult
@RequestMapping("/managesys/server/user")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;
    /**【用户登陆】
     * 192.168.1.26:20234/managesys/server/user/cuser_login
     * */
    @PostMapping("/cuser_login")
    public RespondDto commonUserLogin(@RequestBody InLogin inLogin){
        log.info("【用户登陆】{}",inLogin);
        return loginService.userLogin(inLogin);
    }

    /**【用户修改密码】
     * 192.168.1.26:20234/managesys/server/user/user_change_pw
     * */
    @PostMapping("/user_change_pw")
    public RespondDto commonUserUpdatePassword(@RequestBody InLogin inLogin){
        log.info("【用户修改密码】{}",inLogin);
        return loginService.userUpdatePW(inLogin);
    }
}
