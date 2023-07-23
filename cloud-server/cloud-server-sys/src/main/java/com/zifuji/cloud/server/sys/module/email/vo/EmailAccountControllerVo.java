package com.zifuji.cloud.server.sys.module.email.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailAccountControllerVo extends BaseControllerVo {

    private String name;
}
