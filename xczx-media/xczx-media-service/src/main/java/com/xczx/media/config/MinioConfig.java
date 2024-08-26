package com.xczx.media.config;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description minio配置
 * @author cyhjava
 * @date 2022/9/12 19:32
 * @version 1.0
 */
@Configuration
public class MinioConfig {

    @Autowired
    MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {

        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(minioProperties.getEndpoint())
                        .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                        .build();
        return minioClient;
    }
}
