package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import com.zifuji.cloud.server.base.db.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class ResetManageRoleMo extends BaseControllerMo {

	// 角色名称
	private String roleName;
	// 角色编码
	private String roleCode;
	// 描述
	private String roleDescription;
}
