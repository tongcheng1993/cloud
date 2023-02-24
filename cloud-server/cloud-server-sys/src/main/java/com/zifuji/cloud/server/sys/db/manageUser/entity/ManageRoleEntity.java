package com.zifuji.cloud.server.sys.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_role")
public class ManageRoleEntity extends MyBaseEntity {
    // 角色名称
    private String name;
    // 角色编码
    private String code;
    // 描述
    private String description;
}
