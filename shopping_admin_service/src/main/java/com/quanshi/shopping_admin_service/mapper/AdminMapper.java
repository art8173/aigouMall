package com.quanshi.shopping_admin_service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanshi.shopping_common.entity.Admin;

public interface AdminMapper extends BaseMapper<Admin> {


    Admin findById(Long aid);

    int deleteAdminAllRole(Long aid);
}
