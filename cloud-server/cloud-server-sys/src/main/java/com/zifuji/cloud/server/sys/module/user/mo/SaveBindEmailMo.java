package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "")
public class SaveBindEmailMo extends BaseMo {

    @NotBlank(message = "旧密码不能为空")
    private String passWord;

    @NotBlank(message = "邮箱地址不能为空")
    @Email(message = "请输入正确的邮箱地址")
    private String email;

    @NotBlank(message = "邮箱验证码唯一号不能为空")
    private String redisUuid;

    @NotBlank(message = "验证码不能为空")
    private String value;
}
