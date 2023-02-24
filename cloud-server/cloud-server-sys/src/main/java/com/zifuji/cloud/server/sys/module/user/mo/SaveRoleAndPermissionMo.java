package com.zifuji.cloud.server.sys.module.user.mo;

import java.util.List;

import com.zifuji.cloud.base.bean.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveRoleAndPermissionMo extends BaseMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long roleId;

	private List<Long> permissionIdList;
}
