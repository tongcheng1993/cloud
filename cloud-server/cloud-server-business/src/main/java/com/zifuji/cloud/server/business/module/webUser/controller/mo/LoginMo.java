package com.zifuji.cloud.server.business.module.webUser.controller.mo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class LoginMo {

    @ApiModelProperty()
    @NotBlank(message = "请从正确的入口登录")
    private String redisUuid;
    @ApiModelProperty()
    @NotBlank(message = "验证码不能为空")
    private String redisValue;
    @ApiModelProperty()
    @NotBlank(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty()
    @NotBlank(message = "密码不能为空")
    private String passWord;

}
