package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import com.zifuji.cloud.server.base.db.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class UpdateManageMenuMo extends BaseMo {
	private String label;

	private String name;

	private String path;

	private String component;

	private Boolean showFlag;

	private String icon;
}
