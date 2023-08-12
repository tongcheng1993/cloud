package com.zifuji.cloud.server.sys.module.manageUser.mo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageRoleMenuRelationControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "")
	private Long roleId;
	
	private List<Long> menuIdList;
}
