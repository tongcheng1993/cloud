package com.zifuji.cloud.server.sys.module.file.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.module.file.properties.MinioProperties;
import io.minio.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class MinioComponent {

    private MinioProperties minioProperties;

    private MinioClient minioClient;

    private Snowflake snowflake;
    // 上传文件到一个文件桶中
    public String uploadFile(MultipartFile file) {
        return uploadFile(minioProperties.getDefaultBucketName(), file);
    }

    // 上传文件到一个文件桶中
    public String uploadFile(String bucketName, MultipartFile file) {
        if(StrUtil.isBlank(bucketName)){
            log.info("bucketName为空");
        }
        String fileUuid = snowflake.nextIdStr();
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .stream(file.getInputStream(), file.getSize(), PutObjectArgs.MIN_MULTIPART_SIZE)
                    .object(fileUuid)
                    .build());
        } catch (Exception e) {
            throw new Exception200(e.toString());
        }
        return fileUuid;
    }

    // 从一个文件桶中下载文件
    public InputStream downloadFile(String fileUuid) {
        return downloadFile(minioProperties.getDefaultBucketName(), fileUuid);
    }

    // 从一个文件桶中下载文件
    public InputStream downloadFile(String bucketName, String fileUuid) {
        GetObjectResponse getObjectResponse = null;
        try {
            getObjectResponse = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileUuid)
                    .build());
        } catch (Exception e) {
            throw new Exception200(e.toString());
        }
        return getObjectResponse;
    }
}
