package com.zifuji.cloud.server.business.module.webUser.controller.qo;

import com.zifuji.cloud.server.base.db.BaseQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class QueryWebMenuQo extends BaseQo {

	private String roleId;

}
