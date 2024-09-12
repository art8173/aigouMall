package com.quanshi.shopping_admin_service;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@MapperScan("com.quanshi.shopping_admin_service.mapper")
@EnableDiscoveryClient
@EnableDubbo
@RefreshScope
public class ShoppingAdminServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingAdminServiceApplication.class,args);
    }
}
