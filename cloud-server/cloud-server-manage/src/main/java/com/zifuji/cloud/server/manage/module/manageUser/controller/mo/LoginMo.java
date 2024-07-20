package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class LoginMo {

    @ApiModelProperty("")
    @NotBlank(message = "")
    private String userName;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String passWord;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String redisUuid;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String redisValue;

}
