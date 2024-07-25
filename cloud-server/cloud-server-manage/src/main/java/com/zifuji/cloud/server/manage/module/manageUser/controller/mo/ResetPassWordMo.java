package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.server.base.db.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class ResetPassWordMo extends BaseMo {
	@NotBlank(message = "新密码不能为空")
	private String passWord;

}
