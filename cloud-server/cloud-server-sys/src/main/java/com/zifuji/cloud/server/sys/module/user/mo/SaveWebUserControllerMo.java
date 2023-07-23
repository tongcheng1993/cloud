package com.zifuji.cloud.server.sys.module.user.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveWebUserControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "")
	private String userName;
	
	@NotBlank(message = "")
	private String passWord;
	
	@NotBlank(message = "")
	private String email;
	
	@NotBlank(message = "")
	private String phone;

	

}
