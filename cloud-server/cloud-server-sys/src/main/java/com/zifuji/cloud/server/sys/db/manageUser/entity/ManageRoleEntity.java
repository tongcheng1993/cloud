package com.zifuji.cloud.server.sys.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_role")
public class ManageRoleEntity extends MyBaseEntity {
	// 角色名称
	private String roleName = "";
	// 角色编码
	private String roleCode = "";
	// 描述
	private String roleDescription = "";
}
