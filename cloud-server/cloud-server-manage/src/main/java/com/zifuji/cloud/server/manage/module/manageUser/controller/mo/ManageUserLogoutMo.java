package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ManageUserLogoutMo {
	@NotNull(message = "")
	private Long userId;
}
