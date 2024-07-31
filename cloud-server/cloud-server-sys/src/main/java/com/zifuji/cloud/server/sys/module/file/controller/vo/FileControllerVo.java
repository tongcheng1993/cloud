package com.zifuji.cloud.server.sys.module.file.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zifuji.cloud.server.base.db.BaseVo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FileControllerVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String fileName;

    private String fileType;

    private Long fileByteSize;

    private byte[] fileByte;

    private String mimeType;

    private String fileUrl;

    private InputStream inputStream;

    // 文件上传来源地址
    private String uploadPath = "";
}
