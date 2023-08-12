package com.zifuji.cloud.server.sys.module.user.mo;

import java.util.List;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveRoleAndPermissionControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private List<Long> permissionIdList;
}
