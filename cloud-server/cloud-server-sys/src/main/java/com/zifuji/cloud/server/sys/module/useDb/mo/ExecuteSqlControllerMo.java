package com.zifuji.cloud.server.sys.module.useDb.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ExecuteSqlControllerMo  {

    private String type;

    private String sql;

}
