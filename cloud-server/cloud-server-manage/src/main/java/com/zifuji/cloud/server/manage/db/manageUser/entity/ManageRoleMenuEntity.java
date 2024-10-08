package com.zifuji.cloud.server.manage.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_role_menu")
public class ManageRoleMenuEntity extends MyBaseEntity {
	private Long roleId = -1L;

	private Long menuId = -1L;
}
