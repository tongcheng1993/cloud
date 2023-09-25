package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "")
public class ResetManageUserMo {
    @NotNull(message = "")
    private String id;

    private String userName;

    private String passWord;

    private String shortName;

}
