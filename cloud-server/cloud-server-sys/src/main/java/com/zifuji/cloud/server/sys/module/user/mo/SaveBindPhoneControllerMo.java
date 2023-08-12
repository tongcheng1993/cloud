package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "")
public class SaveBindPhoneControllerMo extends BaseControllerMo {

    @NotBlank(message = "旧密码不能为空")
    private String passWord;

    @NotBlank(message = "手机号不能为空")
    private String phone;

    @NotBlank(message = "验证码唯一号不能为空")
    private String redisUuid;

    @NotBlank(message = "验证码不能为空")
    private String value;
}
