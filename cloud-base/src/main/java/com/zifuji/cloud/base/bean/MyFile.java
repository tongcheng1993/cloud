package com.zifuji.cloud.base.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyFile implements Serializable {

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

}
