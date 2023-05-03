package com.zifuji.cloud.server.sys.module.file.properties;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;

    private Integer port;

    private String accessKey;

    private String secretKey;

    private Boolean secure;

    private String defaultBucketName;


}
