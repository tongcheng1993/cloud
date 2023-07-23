package com.zifuji.cloud.server.sys.module.user.qo;

import java.util.List;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebMenuPageQo extends BaseControllerPageQo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Long> userIds;
	
	private List<String> userNames;
	
	private List<Long> roleIds;
	
	private List<String> roleNames;
	
	private List<String> roleCodes;
}
