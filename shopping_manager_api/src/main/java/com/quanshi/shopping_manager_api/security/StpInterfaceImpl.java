package com.quanshi.shopping_manager_api.security;

import cn.dev33.satoken.stp.StpInterface;
import com.quanshi.shopping_common.entity.Permission;
import com.quanshi.shopping_common.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class StpInterfaceImpl implements StpInterface {

    @DubboReference
    private IAdminService adminService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {


        String username = (String) loginId;

        //获取当前登录用户的所有权限集合
        List<Permission> allPermission = adminService.findAllPermission(username);


        List<String> list = new ArrayList<>();
        for (Permission permission : allPermission) {
            list.add(permission.getUrl());
        }

        //将数据转换成url地址集合
        List<String> permissions = allPermission.stream().map(Permission::getUrl).toList();

        log.debug("permissions :"+permissions);

        return permissions;
    }



    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
