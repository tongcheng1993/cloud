package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "")
public class ResetForgetPassWordControllerMo extends BaseControllerMo {

    @ApiModelProperty(value = "")
    @NotBlank(message = "账户名不能为空")
    private String userName;
    @ApiModelProperty(value = "")
    @NotBlank(message = "新密码不能为空")
    private String passWord;
    @ApiModelProperty(value = "")
    @NotBlank(message = "")
    private String redisUuid;
    @ApiModelProperty(value = "")
    @NotBlank(message = "验证码不能为空")
    private String value;


}
