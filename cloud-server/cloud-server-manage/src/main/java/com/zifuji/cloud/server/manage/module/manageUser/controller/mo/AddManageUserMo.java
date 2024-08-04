package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class AddManageUserMo {

	@NotBlank(message = "用户名不能为空")
	private String userName;
	@NotBlank(message = "昵称不能为空")
	private String shortName;
	@NotBlank(message = "密码不能为空")
	private String passWord;
}
