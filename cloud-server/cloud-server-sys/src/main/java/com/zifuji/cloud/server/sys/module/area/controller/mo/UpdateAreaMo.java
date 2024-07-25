package com.zifuji.cloud.server.sys.module.area.controller.mo;

import com.zifuji.cloud.server.base.db.BaseMo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateAreaMo extends BaseMo{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 双亲节点
	private Long parentId;
	// 区域类型 国家country 省province 市city 区town
	private String type;
	// 名称
	private String name;
	// 编码
	private String code;
}
