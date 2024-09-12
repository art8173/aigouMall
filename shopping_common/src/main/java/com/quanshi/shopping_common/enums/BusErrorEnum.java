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
    SUCCESS(200,"success"),

    //系统异常
    SYSTEM_ERROR(1000,"系统错误"),

    //参数错误
    PARAMETER_ERROR(1001,"参数错误");


    private final Integer code;
    private final String message;
}
