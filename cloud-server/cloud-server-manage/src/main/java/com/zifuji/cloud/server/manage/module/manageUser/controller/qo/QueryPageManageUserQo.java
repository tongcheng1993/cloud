package com.zifuji.cloud.server.manage.module.manageUser.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class QueryPageManageUserQo extends BaseControllerQo {

	@ApiModelProperty("")
	@Size(max = 300, min = 0, message = "")
	private String userName;

	@ApiModelProperty("")
	private String shortName;

}
