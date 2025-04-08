package commons.config;

//package com.rossatti.quarkus_pjc_2025.config;

import io.minio.MinioClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class MinioClientConfig {

    private final MinioProperties properties;

    public MinioClientConfig(MinioProperties properties) {
        this.properties = properties;
    }

    @Produces
    @ApplicationScoped
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.url())
                .credentials(properties.accessKey(), properties.secretKey())
                .build();
    }
}
