package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class AddManagePermissionMo {
	@NotBlank(message = "权限名称不能为空")
	private String perName;

	private String perDescription;
	@NotBlank(message = "微服务名称不能为空")
	private String codeSys;
	@NotBlank(message = "模块名称不能为空")
	private String codeModule;
	@NotBlank(message = "方法名称不能为空")
	private String codeMethod;

}
