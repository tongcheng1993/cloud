package com.zifuji.cloud.server.sys.module.dic.mo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveDicItemControllerMo extends BaseControllerMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;





	@NotNull(message = "")
	private Long dicId;
	

	@ApiModelProperty
	@Size(max = 255, message = "")
	@NotBlank(message = "")
	private String name;
	@Size(max = 255, message = "")
	@NotBlank(message = "")
	private String value;
	@ApiModelProperty()
	@Size(max = 255, message = "")
	private String description;

	

	
	
	// 是否展示
	@NotNull(message = "")
	private Boolean showFlag ;
	// 是否可以选择
	@NotNull(message = "")
	private Boolean disabled ;
	
}
