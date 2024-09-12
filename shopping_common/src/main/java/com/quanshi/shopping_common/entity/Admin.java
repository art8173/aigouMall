package com.quanshi.shopping_common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("t_admin")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    /**
     * exist = false 什么当前字段不是数据库字段，在MyBatisPlus生成SQL语句就会忽略
     */
    @TableField(exist = false)
    private List<Role> roles;
}
