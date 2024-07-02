package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class BindRoleAndMenuDelBeforeMo {

    @NotBlank(message = "")
    private String roleId;

    private List<String> menuIdList;
}
