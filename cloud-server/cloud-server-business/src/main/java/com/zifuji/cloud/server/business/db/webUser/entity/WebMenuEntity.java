package com.zifuji.cloud.server.business.db.webUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_menu")
@ApiModel(value = "外网路由表")
public class WebMenuEntity extends MyBaseEntity {
	
	private Long parentId;

	private String name;

	private String path;

	private String component;
	
	private Integer showFlag;
	
	private String iconFlag;
	

}
