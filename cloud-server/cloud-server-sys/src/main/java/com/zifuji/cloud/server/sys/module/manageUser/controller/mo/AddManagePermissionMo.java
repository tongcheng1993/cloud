package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class AddManagePermissionMo {

    private String perName;
    @NotBlank(message = "")
    private String codeSys;
    @NotBlank(message = "")
    private String codeModule;
    @NotBlank(message = "")
    private String codeMethod;

    private String perDescription;

}
