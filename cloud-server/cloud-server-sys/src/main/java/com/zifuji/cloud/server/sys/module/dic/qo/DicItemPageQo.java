package com.zifuji.cloud.server.sys.module.dic.qo;


import com.zifuji.cloud.base.bean.BasePageQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class DicItemPageQo extends BasePageQo{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private Long dicId;

	private String dicCode;
	
	private String name;
	
	private String code;
	
}
