package com.zifuji.cloud.server.sys.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_role_permission")
public class ManageRolePermissionEntity extends MyBaseEntity {
    private Long roleId;

    private Long permissionId;
}
