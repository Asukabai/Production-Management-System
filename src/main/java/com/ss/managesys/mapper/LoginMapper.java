package com.ss.managesys.mapper;

import com.ss.managesys.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**登陆用
 * @author GuoYanSong
 * @Date 2023-4-11*/

@Mapper
public interface LoginMapper {

    /**查询登录用户姓名密码是否正确
     * @param userName
     * @param userPassword
     * @return selectUser*/
    User selectUser(String userName, String userPassword);

    /**修改密码*/
    Integer updateUserPW(String userName, String oldPW,String newPW );
}
