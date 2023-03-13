package com.zifuji.cloud.server.sys.module.useDb.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ExecuteSqlMo  extends BaseMo {

    private String type;

    private String sql;

}
