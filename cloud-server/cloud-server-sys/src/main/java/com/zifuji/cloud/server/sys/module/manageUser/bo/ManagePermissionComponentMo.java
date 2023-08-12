package com.zifuji.cloud.server.sys.module.manageUser.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManagePermissionComponentMo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;


    private String name;

    private String codeSys;

    private String codeModule;

    private String code;

    private String description;


}
