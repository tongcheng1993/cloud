package com.zifuji.cloud.server.business.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_role_menu")
@ApiModel(value = "外网角色路由中间表")
public class WebRoleMenuEntity extends MyBaseEntity {
	private String roleId;

	private String menuId;
}
