package com.zifuji.cloud.server.sys.module.area.mo;

import lombok.Data;

@Data
public class UpdateAreaMo {

	// id
	private String id;
	// 双亲节点
	private Long parentId;
	// 区域类型 国家country 省province 市city 区town
	private String type;
	// 名称
	private String name;
	// 编码
	private String code;
}
