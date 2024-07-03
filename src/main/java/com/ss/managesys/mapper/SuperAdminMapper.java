package com.ss.managesys.mapper;

import com.ss.managesys.entity.vo.ShowAuthVo;
import com.ss.managesys.entity.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**超级管理员对人员操作
 * @author GuoYanSong
 * @Date 2023-4-12*/
@Mapper
public interface SuperAdminMapper {

    /**查询分页用户的数据**/
    ArrayList<UserVo> selectPageUser(Integer limitIndex,Integer pageSize,String keywords);
    /**查询用户数量 totalDataNum**/
    Integer selectUserNum(String keywords);

    /**依据用户 id 修改用户权限**/
    Integer updateUserAuth(Integer userId,Integer auth);

    /**依据用户 id 删除用户**/
    Integer updateUserDelete(@Param("userIdList") ArrayList<Integer> userIdList);

    /**新增用户*/
    Integer insertUser(String newUserName,Integer auth);

    /**查询所有权限项**/
    ArrayList<ShowAuthVo> selectAllAuth();

}
