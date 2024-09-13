package com.quanshi.shopping_admin_service.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.quanshi.shopping_common.entity.Role;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
public interface RoleMapper extends BaseMapper<Role> {

    Role findById(Long id);
}
