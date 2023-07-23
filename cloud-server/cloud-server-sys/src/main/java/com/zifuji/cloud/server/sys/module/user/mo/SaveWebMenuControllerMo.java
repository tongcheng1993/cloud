package com.zifuji.cloud.server.sys.module.user.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveWebMenuControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id ;

	private Long parentId = 0L;
	
	@NotBlank(message = "")
	private String name;
	
	@NotBlank(message = "")
	private String path;
	
	
	private String component = "/layout/blank";
	
	
	private Integer showFlag = 1;
	
	
	private String iconFlag = "1";
	
	
	private Long sortNum = 0L;
	
	
}
