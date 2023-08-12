package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SendForgetPassWordCaptchaControllerMo extends BaseControllerMo {

    @NotBlank(message = "账户名不能为空")
    private String userName;

}
