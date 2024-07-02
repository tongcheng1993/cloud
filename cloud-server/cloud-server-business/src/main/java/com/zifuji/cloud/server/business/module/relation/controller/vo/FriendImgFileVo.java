package com.zifuji.cloud.server.business.module.relation.controller.vo;

import lombok.Data;

@Data
public class FriendImgFileVo {

    private String fileName;

    private String fileType;

    private Long fileByteSize;

    private byte[] fileByte;

    private String mimeType;

    private String fileUrl;


}
