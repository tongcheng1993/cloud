package com.zifuji.cloud.server.sys.module.manageUser.controller.qo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "")
public class QueryListManageRoleQo {
    @ApiModelProperty("")
    private String userId;
    @ApiModelProperty("")
    private String roleCode;
}
