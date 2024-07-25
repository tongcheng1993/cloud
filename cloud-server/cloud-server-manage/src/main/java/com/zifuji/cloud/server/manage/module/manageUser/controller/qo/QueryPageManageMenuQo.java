package com.zifuji.cloud.server.manage.module.manageUser.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class QueryPageManageMenuQo extends BaseQo {

	private String roleId;

}
