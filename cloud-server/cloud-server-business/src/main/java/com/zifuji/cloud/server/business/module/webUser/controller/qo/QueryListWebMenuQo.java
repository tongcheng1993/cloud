package com.zifuji.cloud.server.business.module.webUser.controller.qo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class QueryListWebMenuQo {

    private String roleId;


}
