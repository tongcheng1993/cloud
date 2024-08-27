package com.zifuji.cloud.server.sys.module.area.controller.mo;

import com.zifuji.cloud.server.base.db.BaseMo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateAreaMo extends BaseMo {

	// 双亲节点
	private Long parentId;
	// 区域类型 国家
	private String type;
	// 名称
	private String name;
	// 编码
	private String code;
}
