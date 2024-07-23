package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class AddManageRoleMo {

	// 角色名称
	@NotBlank(message = "角色名称不能为空")
	private String roleName;
	// 角色编码
	@NotBlank(message = "角色编码不能为空")
	private String roleCode;
	// 描述
	private String roleDescription;

}
