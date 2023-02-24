package com.zifuji.cloud.server.sys.db.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_web_user_role")
@ApiModel(value = "外网用户角色中间表")
public class WebUserRoleEntity extends MyBaseEntity {

	private Long userId;

	private Long roleId;
}
