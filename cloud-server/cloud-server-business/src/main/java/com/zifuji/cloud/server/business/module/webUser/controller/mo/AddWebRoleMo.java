package com.zifuji.cloud.server.business.module.webUser.controller.mo;

import lombok.Data;

@Data
public class AddWebRoleMo {
	// 角色名称
	private String name;
	// 角色编码
	private String code;
	// 描述
	private String description;
}
