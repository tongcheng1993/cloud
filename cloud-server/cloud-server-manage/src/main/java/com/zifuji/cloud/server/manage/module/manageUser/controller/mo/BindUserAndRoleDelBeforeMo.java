package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Data
public class BindUserAndRoleDelBeforeMo {
    @NotNull(message = "")
    private Long userId;

    private List<Long> roleIdList;
}
