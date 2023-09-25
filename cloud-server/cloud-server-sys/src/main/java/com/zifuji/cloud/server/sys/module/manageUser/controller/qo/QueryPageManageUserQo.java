package com.zifuji.cloud.server.sys.module.manageUser.controller.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QueryManageUserQo extends BaseControllerPageQo {




    @ApiModelProperty("")
    @Size(max = 300, min = 0, message = "")
    private String userName;


}
