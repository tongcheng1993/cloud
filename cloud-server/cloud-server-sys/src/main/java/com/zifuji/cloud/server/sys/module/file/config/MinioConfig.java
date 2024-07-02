package com.zifuji.cloud.server.sys.module.file.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class MinioConfig {

    @Value("${zfj.minio.endpoint}")
    private String endpoint;
    @Value("${zfj.minio.port}")
    private Integer port;
    @Value("${zfj.minio.accessKey}")
    private String accessKey;
    @Value("${zfj.minio.secretKey}")
    private String secretKey;
    @Value("${zfj.minio.secure}")
    private Boolean secure;
    @Value("${zfj.minio.defaultBucketName}")
    private String defaultBucketName;

    @Bean
    public MinioClient getMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(endpoint, port, secure)
                .credentials(accessKey, secretKey)
                .build();
        String bucketName = defaultBucketName;
        // 判断默认桶是否存在
        try {
            boolean flag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!flag) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return minioClient;
    }
}
