package com.zifuji.cloud.server.business.db.webUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_user_role")
@ApiModel(value = "外网用户角色中间表")
public class WebUserRoleEntity extends MyBaseEntity {

	private Long userId;

	private Long roleId;
}
