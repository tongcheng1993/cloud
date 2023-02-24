package com.zifuji.cloud.server.sys.module.manageUser.mo;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.zifuji.cloud.base.bean.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageRoleMenuRelationMo extends BaseMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "")
	private Long roleId;
	
	private List<Long> menuIdList;
}
