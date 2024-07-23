package com.zifuji.cloud.server.manage.module.manageUser.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class QueryPageManageRoleQo extends BaseControllerQo {

	@ApiModelProperty("")
	private Long userId;
	@ApiModelProperty("")
	private String roleCode;

}
