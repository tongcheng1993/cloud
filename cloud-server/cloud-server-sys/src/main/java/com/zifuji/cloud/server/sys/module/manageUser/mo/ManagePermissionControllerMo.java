package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManagePermissionControllerMo extends BaseControllerMo {

    private Long id;
    @NotBlank(message = "")
    private String name;
    @NotBlank(message = "")
    private String codeSys;
    @NotBlank(message = "")
    private String codeModule;
    @NotBlank(message = "")
    private String code;
    @NotBlank(message = "")
    private String description;
}
