package com.zifuji.cloud.server.business.module.user.service.mo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Data
@Valid
public class LoginMo {
    @NotBlank(message = "")
    private String redisUuid;
    @NotBlank(message = "")
    private String redisValue;
    @NotBlank(message = "")
    private String userName;
    @NotBlank(message = "")
    private String passWord;
}
