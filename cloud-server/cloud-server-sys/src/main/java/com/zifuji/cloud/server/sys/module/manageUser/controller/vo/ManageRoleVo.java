package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageRoleVo extends BaseControllerVo {


    @ApiModelProperty("角色名称")
    private String roleName;
    @ApiModelProperty("角色编码")
    private String roleCode;
    @ApiModelProperty("描述")
    private String roleDescription;

}
