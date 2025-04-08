package commons.config;
//package com.rossatti.quarkus_pjc_2025.config;

import io.smallrye.config.ConfigMapping;
import jakarta.enterprise.context.ApplicationScoped;

@ConfigMapping(prefix = "minio")
@ApplicationScoped
public interface MinioProperties {

    String url();
    String accessKey();
    String secretKey();
    String bucketName();
}
