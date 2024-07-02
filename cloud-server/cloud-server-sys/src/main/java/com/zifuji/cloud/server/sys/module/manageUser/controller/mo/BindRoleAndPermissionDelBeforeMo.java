package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BindRoleAndPermissionDelBeforeMo {
    @NotNull(message = "")
    private Long roleId;

    private List<Long> permissionIdList;
}
