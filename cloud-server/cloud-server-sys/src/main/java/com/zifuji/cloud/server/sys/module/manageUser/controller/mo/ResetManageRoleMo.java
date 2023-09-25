package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class ResetManageRoleMo {

    private String id;
    // 角色名称
    private String roleName;
    // 角色编码
    private String roleCode;
    // 描述
    private String roleDescription;
}
