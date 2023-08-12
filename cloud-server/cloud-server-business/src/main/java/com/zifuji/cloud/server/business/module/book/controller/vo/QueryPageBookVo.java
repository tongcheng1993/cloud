package com.zifuji.cloud.server.business.module.book.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QueryPageBookVo extends BaseControllerVo {


    private String bookName;

    private String bookAuth;

    private String bookType;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long bookImg;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastSecId;

    private String lastSecName;


}
