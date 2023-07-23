package com.zifuji.cloud.server.sys.module.useDb.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ExecuteSqlControllerMo extends BaseControllerMo {

    private String type;

    private String sql;

}
