package com.zifuji.cloud.base.bean;

import lombok.Data;

import java.io.InputStream;

@Data
public class FileBo {

    private String fileName;

    private Long fileSize;

    private InputStream inputStream;
}
