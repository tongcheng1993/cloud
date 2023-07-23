package com.zifuji.cloud.server.sys.module.area.bo;

import com.zifuji.cloud.base.bean.component.BaseComponentMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AreaComponentMo extends BaseComponentMo {

	/**
	*
	*/
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long parentId;
	private String type;
	private String name;
	private String realName;
	private String code;
}