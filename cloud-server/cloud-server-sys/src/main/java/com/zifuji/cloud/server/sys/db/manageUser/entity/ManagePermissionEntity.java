package com.zifuji.cloud.server.sys.db.manageUser.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_manage_permission")
public class ManagePermissionEntity extends MyBaseEntity {

    private String perName;

    private String codeSys;

    private String codeModule;

    private String codeMethod;

    private String perDescription;
}
