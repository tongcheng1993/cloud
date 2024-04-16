package com.zifuji.cloud.server.business.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_role")
@ApiModel(value = "外网角色表")
public class WebRoleEntity extends MyBaseEntity {
	// 角色名称
	private String name;
	// 角色编码
	private String code;
	// 描述
	private String description;
}
