package com.zifuji.cloud.server.business.db.webUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_role_permission")
@ApiModel(value = "外网角色权限中间表")
public class WebRolePermissionEntity extends MyBaseEntity {

	private Long roleId;

	private Long permissionId;
}
