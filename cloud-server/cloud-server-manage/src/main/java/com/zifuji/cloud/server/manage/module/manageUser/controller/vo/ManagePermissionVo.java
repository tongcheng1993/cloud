package com.zifuji.cloud.server.manage.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManagePermissionVo extends BaseVo {

    private String perName;

    private String codeSys;

    private String codeModule;

    private String codeMethod;

    private String perDescription;
}
