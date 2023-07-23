package com.zifuji.cloud.server.sys.module.email.qo;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class EmailTemplatePageQo extends BaseControllerPageQo {

    private String name;
}
