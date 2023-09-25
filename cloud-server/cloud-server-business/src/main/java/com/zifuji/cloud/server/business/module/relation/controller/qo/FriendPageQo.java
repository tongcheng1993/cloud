package com.zifuji.cloud.server.business.module.relation.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FriendPageQo extends Page {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String sex;
	
}
