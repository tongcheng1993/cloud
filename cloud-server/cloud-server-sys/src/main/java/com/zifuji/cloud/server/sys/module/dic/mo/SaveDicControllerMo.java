package com.zifuji.cloud.server.sys.module.dic.mo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveDicControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@ApiModelProperty
	@Size(max = 255,message = "")
	@NotBlank(message = "")
	private String name;
	
	
	@ApiModelProperty
	@Size(max = 255,message = "")
	@NotBlank(message = "")
	private String code;
	
	
	@ApiModelProperty()
	@Size(max = 255,message = "")
	private String description;
	

}
