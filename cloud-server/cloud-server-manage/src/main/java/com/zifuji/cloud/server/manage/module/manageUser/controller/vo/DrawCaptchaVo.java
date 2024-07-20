package com.zifuji.cloud.server.manage.module.manageUser.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "")
public class DrawCaptchaVo {

    @ApiModelProperty("")
    private String redisUuid;

    @ApiModelProperty("")
    private byte[] imgBytes;
}
