package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotNull;

import java.util.List;

@Data
public class BindRoleAndMenuDelBeforeMo {

	@NotNull(message = "")
	private Long roleId;

	private List<Long> menuIdList;
}
