package com.quanshi.shopping_common.exception;


import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.result.BaseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理类
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 统一处理业务逻辑异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusException.class)
    public BaseResult busExceptionHandler(BusException e){
        System.out.println("e = " + e);
        return BaseResult.error(e.getCode(),e.getMsg());
    }


    //其他异常
    @ExceptionHandler(Exception.class)
    public BaseResult otherException(Exception e){
        e.printStackTrace();
        if(e.getClass().getSimpleName().equals("NotPermissionException")){
            Integer code = BusErrorEnum.NO_PERMISSION_ERROR.getCode();
            String message = BusErrorEnum.NO_PERMISSION_ERROR.getMessage();
            return BaseResult.error(code,message);
        }


        Integer code = BusErrorEnum.SYSTEM_ERROR.getCode();
        String message = BusErrorEnum.SYSTEM_ERROR.getMessage();
        return BaseResult.error(code,message);
    }


}
