package com.ss.managesys.service.impl;

import com.ss.managesys.entity.code.ResultCode;
import com.ss.managesys.entity.dto.RespondDto;
import com.ss.managesys.entity.vo.InSuperAdmin;
import com.ss.managesys.entity.vo.ShowAuthVo;
import com.ss.managesys.entity.vo.SuperAdminVo;
import com.ss.managesys.entity.vo.UserVo;
import com.ss.managesys.mapper.SuperAdminMapper;
import com.ss.managesys.service.SuperAdminService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * 超级管理员对员工操作
 *
 * @author GuoYanSong
 * @Date 2023-4-12
 */
@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Resource
    private SuperAdminMapper superAdminMapper;

    /**【展示所有权限项】**/
    @Override
    public RespondDto showAllAuth() {
        ArrayList<ShowAuthVo> showAuthVos= superAdminMapper.selectAllAuth();
        return new RespondDto(ResultCode.OK,"展示所有权限项成功",showAuthVos);
    }

    /**【新增用户】*/
    @Override
    public RespondDto addUser(@NonNull InSuperAdmin inSuperAdmin) {
        if(inSuperAdmin.getNewUserName() != null && inSuperAdmin.getAuth() != null){
            Integer integer = superAdminMapper.insertUser(inSuperAdmin.getNewUserName(), inSuperAdmin.getAuth());
            if(integer == 1){
                return new RespondDto(ResultCode.OK,"用户新增改成功");
            }else {
                return new RespondDto(ResultCode.NO,"没有用户被新增");
            }
        }else {
            return new RespondDto(ResultCode.NO,"入参有为null值");
        }
    }

    /**【删除用户】*/
    @Override
    public RespondDto deleteUser(@NonNull InSuperAdmin inSuperAdmin) {
        if(inSuperAdmin.getUserIDList().size() > 0){
            Integer integer = superAdminMapper.updateUserDelete(inSuperAdmin.getUserIDList());
            if(integer >= 1){
                return new RespondDto(ResultCode.OK,"用户删除改成功");
            }else {
                return new RespondDto(ResultCode.NO,"没有人被删除或者所需删除用户已被删除");
            }
        }else {
            return new RespondDto(ResultCode.NO,"入参userIDList为空");
        }
    }

    /**
     * 【修改用户】依据用户id修改用户权限
     */
    @Override
    public RespondDto updateUserAuth(InSuperAdmin inSuperAdmin) {
        if(inSuperAdmin.getUserId() != null && inSuperAdmin.getAuth() != null){
            Integer integer = superAdminMapper.updateUserAuth(inSuperAdmin.getUserId(), inSuperAdmin.getAuth());
            if(integer == 1){
                return new RespondDto(ResultCode.OK,"用户权限修改成功");
            }else {
                return new RespondDto(ResultCode.NO,"没有人的权限被修改");
            }
        }else {
            return new RespondDto(ResultCode.NO,"入参有为null值");
        }

    }

    /**【用户列表】 展示所有用户
     * 1、查出符合条件的数据总条数 totalDataNum；
     * 2、计算limit 起始索引 limitIndex；
     * 3、查询展示数据；
     * */
    @Override
    public RespondDto showAllUsers(InSuperAdmin inSuperAdmin) {
//        ArrayList<UserVo> users = superAdminMapper.selectAllUser();
//        return new RespondDto(ResultCode.OK,"查询全部用户成功",users);

//        拼sql时防止拼null
        if(inSuperAdmin.getKeywords() == null){
            inSuperAdmin.setKeywords("");
        }

        Integer totalDataNum = superAdminMapper.selectUserNum(inSuperAdmin.getKeywords());
        Integer limitIndex = (inSuperAdmin.getCurrentPage()-1) * inSuperAdmin.getPageSize();

        ArrayList<UserVo> userVos = superAdminMapper.selectPageUser(limitIndex, inSuperAdmin.getPageSize(), inSuperAdmin.getKeywords());

        SuperAdminVo superAdminVo = new SuperAdminVo();
        superAdminVo.setTotalDataNum(totalDataNum);
        superAdminVo.setDataDetail(userVos);

        return new RespondDto(ResultCode.OK,"分页查询全部用户成功",superAdminVo);
    }
}
