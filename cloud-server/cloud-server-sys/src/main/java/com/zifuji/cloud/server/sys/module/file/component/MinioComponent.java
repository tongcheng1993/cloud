package com.zifuji.cloud.server.sys.module.file.component;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.module.file.properties.MinioProperties;
import io.minio.*;
import io.minio.errors.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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
    private String uploadFile(String bucketName, MultipartFile file) {
        if (StrUtil.isBlank(bucketName)) {
            log.info("bucketName为空");
        }
        log.info(file.getContentType());
        String fileUuid = snowflake.nextIdStr() + file.getOriginalFilename();
        try {
            ObjectWriteResponse response =  minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(fileUuid)
                    .contentType(file.getContentType())
                    .build());
        } catch (Exception e) {
            throw new Exception20000(e.toString());
        }
        return fileUuid;
    }

    // 从一个文件桶中下载文件
    public InputStream downloadFile(String fileUuid) {
        return downloadFile(minioProperties.getDefaultBucketName(), fileUuid);
    }

    // 从一个文件桶中下载文件
    private InputStream downloadFile(String bucketName, String fileUuid) {
        GetObjectResponse getObjectResponse = null;
        try {
            getObjectResponse = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileUuid)
                    .build());
        } catch (Exception e) {
            throw new Exception20000(e.toString());
        }
        return getObjectResponse;
    }
}
