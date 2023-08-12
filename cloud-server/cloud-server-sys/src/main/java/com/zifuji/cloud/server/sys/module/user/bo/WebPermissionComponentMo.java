package com.zifuji.cloud.server.sys.module.user.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebPermissionComponentMo  {


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
