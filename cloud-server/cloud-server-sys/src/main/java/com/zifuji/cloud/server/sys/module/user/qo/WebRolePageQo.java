package com.zifuji.cloud.server.sys.module.user.qo;

import java.util.List;

import com.zifuji.cloud.base.bean.BasePageQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebRolePageQo extends BasePageQo{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Long> userIds;
	
	private List<String> userNames;
}
