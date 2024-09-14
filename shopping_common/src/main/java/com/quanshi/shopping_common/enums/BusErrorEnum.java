package com.quanshi.shopping_common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 统一返回码枚举
 */

@Getter
@AllArgsConstructor
public enum BusErrorEnum {

    //操作成功
    SUCCESS(200, "success"),

    //系统异常
    SYSTEM_ERROR(1000, "系统错误"),

    //参数错误
    PARAMETER_ERROR(1001, "参数错误"),
    //删除权限错误
    PERMISSION_IN_USE(1002, "当前权限还有角色在使用，不能删除"),

    NO_LOGIN_ERROR(401, "未登录"),


    USERNAME_OR_PASSWORD_ERROR(1003, "账号密码错误"),

    NO_PERMISSION_ERROR(1004, "没有访问权限"),

    PRODUCT_TYPE_DELETE_ERROR(1006, "当前分类还有子分类，不能删除"),

    PRODUCT_TYPE_LEVEL_ERROR(1005, "商品分类最多三级");

    private final Integer code;
    private final String message;
}
