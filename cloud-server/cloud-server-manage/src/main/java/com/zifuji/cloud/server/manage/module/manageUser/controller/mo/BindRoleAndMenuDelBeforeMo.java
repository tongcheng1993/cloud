package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BindRoleAndMenuDelBeforeMo {

    @NotBlank(message = "")
    private Long roleId;

    private List<Long> menuIdList;
}
