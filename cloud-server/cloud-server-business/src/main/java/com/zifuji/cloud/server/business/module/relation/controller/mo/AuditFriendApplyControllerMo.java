package com.zifuji.cloud.server.business.module.relation.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.server.base.db.BaseControllerMo;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AuditFriendApplyControllerMo extends BaseControllerMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupName;

	private String noteName;

	@NotBlank(message = "")
	private String auditStatus;

}
