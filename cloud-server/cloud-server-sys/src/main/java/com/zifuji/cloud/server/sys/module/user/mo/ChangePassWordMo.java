package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ChangePassWordMo extends BaseMo {

    @NotBlank(message = "旧密码不能为空")
    private String passWord;
    @NotBlank(message = "新密码不能为空")
    private String newPassWord;
    @NotBlank(message = "第二次新密码不能为空")
    private String newPassWordSec;

}
