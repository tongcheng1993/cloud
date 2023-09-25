package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class QueryListManagePermissionVo {

    private String perName;

    private String codeSys;

    private String codeModule;

    private String codeMethod;

    private String perDescription;
}
