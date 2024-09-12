package com.quanshi.shopping_goods_service;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDubbo
@MapperScan("com.quanshi.shopping_goods_service")
@RefreshScope // 配置动态刷新
public class ShoppingGoodsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingGoodsServiceApplication.class,args);
    }
}
