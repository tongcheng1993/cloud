package com.zifuji.cloud.server.business.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_permission")
@ApiModel(value = "外网权限表")
public class WebPermissionEntity extends MyBaseEntity {
	
	private String name;

	private String codeSys;

	private String codeModule;

	private String code;

	private String description;
}
