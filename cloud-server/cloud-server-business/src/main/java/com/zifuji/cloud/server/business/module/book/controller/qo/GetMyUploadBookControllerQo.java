package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;

@Data
@EqualsAndHashCode(callSuper = false)
@Valid
@ApiModel
public class GetMyUploadBookControllerQo extends Page {

    @ApiModelProperty
    private String bookName;

    private String bookAuth;

    private String bookType;

}
