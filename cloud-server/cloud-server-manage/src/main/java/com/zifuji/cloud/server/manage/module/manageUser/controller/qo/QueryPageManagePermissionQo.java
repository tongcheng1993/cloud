package com.zifuji.cloud.server.manage.module.manageUser.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class QueryPageManagePermissionQo extends BaseQo {

	private Long roleId;

}
