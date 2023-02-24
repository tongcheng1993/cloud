package com.zifuji.cloud.server.sys.module.manageUser.mo;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseMo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuMo extends BaseMo {
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
