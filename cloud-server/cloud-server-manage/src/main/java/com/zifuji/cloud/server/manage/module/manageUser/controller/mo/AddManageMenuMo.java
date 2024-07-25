package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class AddManageMenuMo {
	@NotNull(message = "上级路由不能为空")
	private Long parentId;
	@NotBlank(message = "路由标题不能为空")
	private String label;
	@NotBlank(message = "路由名称不能为空")
	private String name;
	@NotBlank(message = "路由url不能为空")
	private String path;

	private String component="/layout/blank";

	private Boolean showFlag= true;

	private String icon = "el-icon-eleme";
}
