package com.ss.managesys.service.impl;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.po.User;
import com.ss.managesys.entity.vo.InLogin;
import com.ss.managesys.entity.vo.LoginVo;
import com.ss.managesys.mapper.LoginMapper;
import com.ss.managesys.service.LoginService;
import com.ss.managesys.util.JWTUtils;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登陆
 *
 * @author GuoYanSong
 * @Date 2023-4-11
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private JWTUtils jwtUtils;

    /**修改密码
     * 1、查找该用户是否存在；
     * 2、存在进行密码修改不存在返回NO；*/
    @Override
    public RespondDto userUpdatePW(@NonNull InLogin inLogin) {
        User user = loginMapper.selectUser(inLogin.getUserName(), inLogin.getOldPW());
        if (user!= null){
            loginMapper.updateUserPW(inLogin.getUserName(),inLogin.getOldPW(),inLogin.getNewPW());
            return new RespondDto(ResultCode.OK,"用户密码修改成功");
        }else {
            return new RespondDto(ResultCode.NO,"用户不存在或旧密码错误");
        }
    }

    /**登录*/
    @Override
    public RespondDto userLogin(@NonNull InLogin inLogin) {

        User user = loginMapper.selectUser(inLogin.getUserName(), inLogin.getPassWord());
        System.out.println(user);
        if (user != null) {
            LoginVo loginVo = new LoginVo();

            loginVo.setUserID(user.getUserId());
            loginVo.setUN(user.getUserName());
            loginVo.setToken(jwtUtils.tokenMaker(user));
            loginVo.setAuth(user.getAuth());

            return new RespondDto(ResultCode.OK,"用户登录成功",loginVo);
        }
            return new RespondDto(ResultCode.USER_NONE,"用户登录失败，用户不存在或密码错误");
    }
}
