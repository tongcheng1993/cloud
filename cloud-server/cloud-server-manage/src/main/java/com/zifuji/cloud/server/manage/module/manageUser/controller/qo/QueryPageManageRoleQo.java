package com.zifuji.cloud.server.manage.module.manageUser.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class QueryPageManageRoleQo extends Page {

    @ApiModelProperty("")
    private Long userId;
    @ApiModelProperty("")
    private String roleCode;

}
