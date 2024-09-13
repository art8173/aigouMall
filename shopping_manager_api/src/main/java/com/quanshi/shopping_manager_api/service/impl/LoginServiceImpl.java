package com.quanshi.shopping_manager_api.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.result.BaseResult;
import com.quanshi.shopping_common.service.IAdminService;
import com.quanshi.shopping_manager_api.service.ILoginService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements ILoginService {

    @DubboReference
    private IAdminService adminService;

    @Override
    public BaseResult login(String username, String password) {
        /**
         * 登录的思路
         * 1，先根据账号去查询数据库中是否存在此账号
         *  不存在：账号密码错误
         * 2，根据第一步账号查询的用户信息进行密码的比对
         *   相同：登录成功（发放登录令牌token）
         *   不同：账号密码错误
         */
        Admin admin = adminService.findByUsername(username);

        //账号存在
        if (Objects.nonNull(admin)) {
            //比较密码
            if(admin.getPassword().equals(SaSecureUtil.md5(password))){
                //使用Sa-token完成认证（登录）
                StpUtil.login(admin.getUsername());
                //获取登录token
                String token = StpUtil.getTokenInfo().getTokenValue();
               return BaseResult.success(token);
            }
        }
        return BaseResult.error(BusErrorEnum.USERNAME_OR_PASSWORD_ERROR.getCode(), BusErrorEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }
}
