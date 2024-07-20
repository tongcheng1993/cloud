package com.zifuji.cloud.server.manage.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_user_role")
public class ManageUserRoleEntity extends MyBaseEntity {

	private Long userId = -1L;

	private Long roleId = -1L;
}
