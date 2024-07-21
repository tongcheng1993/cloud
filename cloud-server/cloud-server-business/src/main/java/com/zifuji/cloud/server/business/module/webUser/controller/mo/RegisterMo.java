package com.zifuji.cloud.server.business.module.webUser.controller.mo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class RegisterMo {


    @ApiModelProperty()
    @NotBlank(message = "请从正确的入口注册")
    private String redisUuid;
    @ApiModelProperty()
    @NotBlank(message = "请输入验证码")
    private String redisValue;
    @ApiModelProperty()
    @NotBlank(message = "请输入用户名")
    private String userName;
    @ApiModelProperty()
    @NotBlank(message = "请输入密码")
    private String passWord;



}
