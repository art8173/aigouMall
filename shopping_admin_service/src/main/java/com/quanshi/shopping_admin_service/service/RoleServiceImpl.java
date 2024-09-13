package com.quanshi.shopping_admin_service.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_admin_service.mapper.RoleMapper;
import com.quanshi.shopping_admin_service.mapper.RolePermissionMapper;
import com.quanshi.shopping_common.entity.Role;
import com.quanshi.shopping_common.entity.RolePermission;
import com.quanshi.shopping_common.service.IRoleService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@DubboService
public class RoleServiceImpl implements IRoleService {


    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateById(role);
    }

    @Override
    public void delete(Long id) {
        roleMapper.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        Role role = roleMapper.findById(id);
        return role;
    }

    @Override
    public List<Role> findAll() {

        List<Role> roles = roleMapper.selectList(null);

        return roles;
    }

    @Override
    public Page<Role> search(int page, int size) {
        Page<Role> paramsPage = new Page<>(page,size);
        Page<Role> roleIPage = roleMapper.selectPage(paramsPage, null);
        return roleIPage;
    }

    @Override
    public void updatePermissionToRole(Long rid, Long[] pids) {
        /**
         * 修改角色对应的权限思路
         * 1,先删除t_role_permission 中间表所有当前角色的数据
         * 2,再将新的权限信息插入到 t_role_permission
         */

        //创建更新条件（删除，修改）
        LambdaUpdateWrapper<RolePermission> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(RolePermission::getRid,rid);
        //delete from t_role_permission where rid = ?
        rolePermissionMapper.delete(updateWrapper);

        //新增新的权限信息
        for (Long pid : pids) {
            //创建角色权限对象
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRid(rid);
            rolePermission.setPid(pid);
            //新增
            rolePermissionMapper.insert(rolePermission);
        }
    }
}
