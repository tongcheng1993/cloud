package com.zifuji.cloud.server.sys.module.manageUser.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuControllerMo extends BaseControllerMo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	@ApiModelProperty(name = "")
	private Long parentId = 0L;

	private Integer sortNum = 0 ;
	
	@NotBlank(message = "")
	private String name;

	@NotBlank(message = "")
	private String path;

	private String component = "/layout/blank";

	private Integer showFlag = 1;

	private String iconFlag = "1";

}
