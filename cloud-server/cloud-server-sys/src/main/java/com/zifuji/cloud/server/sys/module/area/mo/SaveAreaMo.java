package com.zifuji.cloud.server.sys.module.area.mo;

import com.zifuji.cloud.base.bean.BaseMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveAreaMo extends BaseMo{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 双亲节点
	private Long parentId;
	// 区域类型 国家country 省province 市city 区town
	private String type;
	// 简称
	private String name;
	// 全称
	private String realName;
	// 编码
	private String code;
}
