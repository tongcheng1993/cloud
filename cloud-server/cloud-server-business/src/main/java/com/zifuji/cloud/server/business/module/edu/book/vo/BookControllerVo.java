package com.zifuji.cloud.server.business.module.edu.book.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BookControllerVo extends BaseControllerVo {


    private String name;

    private String auth;

    private String type;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long img;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long lastSecId;

    private String lastSecName;


}
