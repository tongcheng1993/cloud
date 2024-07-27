package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddManageUserManageRoleRelationAndLogoutMo {
	@NotNull(message = "")
	private Long userId;
	@NotBlank(message = "")
	private String roleCode;

}
