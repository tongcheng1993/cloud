package com.zifuji.cloud.server.sys.module.file.config;

import com.zifuji.cloud.server.sys.module.file.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Slf4j
@Configuration
@AllArgsConstructor
public class MinioConfig {

    private MinioProperties minioProperties;

    @Bean
    public MinioClient getMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getEndpoint(), minioProperties.getPort(), minioProperties.getSecure())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        String bucketName = minioProperties.getDefaultBucketName();
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
