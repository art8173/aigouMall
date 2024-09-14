package com.quanshi.shopping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanshi.shopping_common.entity.Admin;
import com.quanshi.shopping_common.entity.Permission;

import java.util.List;

public interface AdminMapper extends BaseMapper<Admin> {


    Admin findById(Long aid);

    int deleteAdminAllRole(Long aid);

    List<Permission> findAllPermission(String username);
}
