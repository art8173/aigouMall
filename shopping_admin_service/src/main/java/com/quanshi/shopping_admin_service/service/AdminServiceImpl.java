package com.quanshi.shopping_admin_service.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_admin_service.mapper.AdminMapper;
import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.service.IAdminService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

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
    public void updateRoleToAdmin(Long aid, Long[] rids) {

    }
}
