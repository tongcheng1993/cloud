package com.zifuji.cloud.server.business.module.friend.relation.qo;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendPageQo extends BaseControllerPageQo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sex;
	
}
