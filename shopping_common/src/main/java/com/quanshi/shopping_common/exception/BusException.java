package com.quanshi.shopping_common.exception;

import com.quanshi.shopping_common.enums.BusErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusException extends RuntimeException{

    // 状态码
    private Integer code;
    // 错误消息
    private String msg;

    public BusException(BusErrorEnum busErrorEnum){
        this.code = busErrorEnum.getCode();
        this.msg = busErrorEnum.getMessage();
    }

    public static BusException busException(BusErrorEnum busErrorEnum){
        throw new BusException(busErrorEnum);
    }
}
