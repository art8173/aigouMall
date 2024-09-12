package com.quanshi.shopping_common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.quanshi.shopping_common.entity.Admin;

public interface IAdminService {
    // 新增管理员
    void add(Admin admin);
    // 修改管理员
    void update(Admin admin);
    // 删除管理员
    void deleteById(Long aid);
    // 根据id查询管理员
    Admin findById(Long aid);
    // 分页查询管理员
    Page<Admin> search(int page, int size);
    // 修改管理员角色
    void updateRoleToAdmin(Long aid, Long[] rids);
}
