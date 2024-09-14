package com.quanshi.shopping_manager_api.config;


import cn.dev33.satoken.interceptor.SaInterceptor;
import com.quanshi.shopping_manager_api.interceptor.CheckLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurer 创建WebMVC的配置，用于注册拦拦截器等
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry 拦截器的注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册简单登录拦击诶去
        registry.addInterceptor(new CheckLoginInterceptor())
                .addPathPatterns("/**")//拦截所有的请求
                .excludePathPatterns("/admin/login");//放行不需要拦截的资源，如登录，静态资源(图片,css)等


        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor()).addPathPatterns("/**");

    }
}
