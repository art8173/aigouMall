package com.quanshi.shopping_common.result;

import java.io.Serializable;

public class BaseResult implements Serializable {

    private Integer code;
    //逻辑状态码（开发者自定义）不是http响应状态码，
    // 一般200认为成功，500任务逻辑有问题

    private String message;//message
    //返回给前端的消息，一般都是逻辑错误以后给前端返回提示信息

    private Object data;
    //返回给前端的数据对象，比如单行查询，多行查询

    /**
     * 下面是常用快捷方法
     */


    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.code = 200;
        result.message = "success";
        return result;
    }

    public static BaseResult success(Object data, String message) {
        BaseResult result = new BaseResult();
        result.code = 200;
        result.message = message;
        return result;
    }

    public static BaseResult success(Object data) {
        BaseResult result = new BaseResult();
        result.code = 200;
        result.data = data;
        return result;
    }

    public static BaseResult error() {
        BaseResult result = new BaseResult();
        result.code = 500;
        result.message = "error";
        return result;
    }

    public static BaseResult error(String message) {
        BaseResult result = new BaseResult();
        result.code = 500;
        result.message = message;
        return result;
    }
    public static BaseResult error(Integer code,String message) {
        BaseResult result = new BaseResult();
        result.code = code;
        result.message = message;
        return result;
    }
    public static BaseResult error(int code, String message) {
        BaseResult result = new BaseResult();
        result.code = code;
        result.message = message;
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
