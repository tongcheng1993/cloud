package com.zifuji.cloud.server.business.module.webUser.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class WebRoleVo extends BaseVo {
	// 角色名称
	private String name;
	// 角色编码
	private String code;
	// 描述
	private String description;
}
