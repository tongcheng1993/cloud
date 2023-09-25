package com.zifuji.cloud.server.sys.module.manageUser.controller.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class LoginControllerMo {

    @ApiModelProperty("")
    @NotBlank(message = "")
    private String userName;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String passWord;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String redisUuid;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String value;

}
