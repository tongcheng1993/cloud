package com.zifuji.cloud.server.business.module.book.service.bo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookSectionBo {

    private Long id;

    private String createBy;

    private LocalDateTime createTime;

    private String updateBy;

    private LocalDateTime updateTime;

    private Long sortNum;

    private Boolean delFlag;

   private String parentId;

    private String sectionName;

    private String sectionContent;

    private Long nextId;

    private Long lastId;

}
