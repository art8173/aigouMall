package com.quanshi.shopping_admin_service.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_admin_service.mapper.PermissionMapper;
import com.quanshi.shopping_admin_service.mapper.RolePermissionMapper;
import com.quanshi.shopping_common.entity.Permission;
import com.quanshi.shopping_common.entity.RolePermission;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.exception.BusException;
import com.quanshi.shopping_common.service.IPermissionService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DubboService
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateById(permission);
    }

    @Override
    public void delete(Long id) {
        //先判断当前权限是否还有角色在使用，如果有，不能删除
        LambdaQueryWrapper<RolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RolePermission::getPid,id);
        Long count = rolePermissionMapper.selectCount(queryWrapper);
        //中间表中有这个权限
        if(count>0){
            BusException.busException(BusErrorEnum.PERMISSION_IN_USE);
        }
        permissionMapper.deleteById(id);
    }

    @Override
    public Permission findById(Long id) {
        return permissionMapper.selectById(id);
    }

    @Override
    public Page<Permission> search(int page, int size) {
        Page<Permission> paramsPage = new Page<>();
        Page<Permission> resultPage = permissionMapper.selectPage(paramsPage, null);
        return resultPage;
    }

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectList(null);
    }
}
