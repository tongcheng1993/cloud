package com.zifuji.cloud.server.business.module.edu.book.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BookServiceVo {

    private Long id;

    private String bName;

    private String bAuth;

    private String bType;

    private Long bImg;

    private Long lastSId;

    private String lastSName;


}
