package com.quanshi.shopping_manager_api.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.quanshi.shopping_common.enums.BusErrorEnum;
import com.quanshi.shopping_common.exception.BusException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class CheckLoginInterceptor implements HandlerInterceptor {

    /**
     * 控制器执行之前执行，true，放行 ，false不放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //判断是否登录
        if(!StpUtil.isLogin()){
            //抛出逻辑异常
            BusException.busException(BusErrorEnum.NO_LOGIN_ERROR);
        }

        return true;
    }

    /**
     * 控制器执行之后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 请求完成执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
