package com.ss.managesys.service;

import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InLogin;

/**登陆
 * @author GuoYanSong
 * @Date 2023-4-11*/
public interface LoginService {

    /**登陆*/
    RespondDto userLogin (InLogin inLogin);

    /**修改密码*/
    RespondDto userUpdatePW (InLogin inLogin);
}
