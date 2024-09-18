package com.quanshi.shopping_file_service;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDubbo
@RefreshScope
@EnableDiscoveryClient
public class ShoppingFileServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ShoppingFileServiceApplication.class,args);
    }
}
