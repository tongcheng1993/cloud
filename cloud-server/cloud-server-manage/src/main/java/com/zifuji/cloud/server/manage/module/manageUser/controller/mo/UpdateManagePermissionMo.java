package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "")
public class UpdateManagePermissionMo {
    @NotNull(message = "id")
    private Long tableId;

    private String perName;


    private String perDescription;

}
