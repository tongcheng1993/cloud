package com.zifuji.cloud.server.sys.module.user.mo;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveWebPermissionControllerMo extends BaseControllerMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "")
    private String name;

    private String codeSys;

    private String codeModule;

    @NotBlank(message = "")
    private String code;

    private String description;


}
