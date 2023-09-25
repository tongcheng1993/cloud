package com.zifuji.cloud.server.business.module.webUser.controller.mo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@Valid
@ApiModel
public class LoginControllerMo {

    @ApiModelProperty()
    @NotBlank(message = "")
    private String redisUuid;
    @ApiModelProperty()
    @NotBlank(message = "")
    private String redisValue;
    @ApiModelProperty()
    @NotBlank(message = "")
    private String userName;
    @ApiModelProperty()
    @NotBlank(message = "")
    private String passWord;

}
