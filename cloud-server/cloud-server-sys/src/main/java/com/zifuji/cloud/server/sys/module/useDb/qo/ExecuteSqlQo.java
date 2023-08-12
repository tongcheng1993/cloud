package com.zifuji.cloud.server.sys.module.useDb.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ExecuteSqlQo extends BaseControllerPageQo {

    private long size = 1000;

    private long current = 1;

    private String type;

    private String sql;

}
