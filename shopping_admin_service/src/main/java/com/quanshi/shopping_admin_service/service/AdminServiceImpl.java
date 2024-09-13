package com.quanshi.shopping_admin_service.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_admin_service.mapper.AdminMapper;
import com.quanshi.shopping_admin_service.mapper.AdminRoleMapper;
import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.entity.AdminRole;
import com.quanshi.shopping_common.service.IAdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@DubboService
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    public void add(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public void update(Admin admin) {
        adminMapper.updateById(admin);
    }

    @Override
    public void deleteById(Long aid) {
        adminMapper.deleteById(aid);
    }

    @Override
    public Admin findById(Long aid) {
        return adminMapper.findById(aid);
    }

    @Override
    public Page<Admin> search(int page, int size) {

        //创建分页对象
        Page<Admin> paramPage = new Page<>(page, size);
        //分页查询
        Page<Admin> pageResult = adminMapper.selectPage(paramPage, null);
        return pageResult;
    }

    @Override
    @Transactional //事务注解
    public void updateRoleToAdmin(Long aid, Long[] rids) {
        /**
         * 给当前管理分配角色思路
         * 1.先删除这个管理员之前的所有角色数据
         * 2.在重新赋予新的角色数据
         */

        //创建删除条件
        LambdaUpdateWrapper<AdminRole> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AdminRole::getAid,aid);
        //等价与 delete from t_admin_role where aid = xxx
        adminRoleMapper.delete(updateWrapper);
//        int row = adminMapper.deleteAdminAllRole(aid);

        //System.out.println(1/0);

        //循环查询当前用户要赋予的角色信息
        for (Long rid : rids) {
            AdminRole adminRole = new AdminRole();
            adminRole.setAid(aid);
            adminRole.setRid(rid);
            adminRoleMapper.insert(adminRole);
        }
    }

    @Override
    public Admin findByUsername(String username) {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername,username);
        return adminMapper.selectOne(queryWrapper);
    }
}
