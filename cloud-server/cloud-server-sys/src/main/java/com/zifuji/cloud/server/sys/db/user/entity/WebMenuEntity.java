package com.zifuji.cloud.server.sys.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_menu")
@ApiModel(value = "外网路由表")
public class WebMenuEntity extends MyBaseEntity {
	
	private Long parentId;

	private String name;

	private String path;

	private String component;
	
	private Integer showFlag;
	
	private String iconFlag;
	

}
