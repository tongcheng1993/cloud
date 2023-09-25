package com.zifuji.cloud.server.business.module.book.service.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookBo {

    private String id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Long sortNum;

    private Boolean delFlag;

   private String parentId;

    private String bookName;

    private String isbn;

    private String bookAuth;

    private String bookType;

    private Long bookImg;



}
