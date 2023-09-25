package com.zifuji.cloud.server.sys.module.area.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AreaComponentMo  {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private String id;
	private Long parentId;
	private String type;
	private String name;
	private String realName;
	private String code;
}
