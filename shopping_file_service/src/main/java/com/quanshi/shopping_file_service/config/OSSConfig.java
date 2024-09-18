package com.quanshi.shopping_file_service.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "upload")
@Data
public class OSSConfig {

    private String accessKey;

    private String secretKey;

    private String bucketName;

    private String serverUrl;

    private String endpoint;
}
