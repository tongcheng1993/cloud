package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendBindEmailCaptchaControllerMo extends BaseControllerMo {

    @NotBlank(message = "邮箱地址不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String email;


}
