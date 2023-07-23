package com.zifuji.cloud.server.business.module.edu.book.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BookSectionServiceVo {

    private Long secId;

    private String secName;

    private String secContent;
}
