package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(description = "")
public class DrawCaptchaVo {

    @ApiModelProperty("")
    private String redisUuid;

    @ApiModelProperty("")
    private byte[] imgBytes;
}
