package com.zifuji.cloud.server.sys.module.area.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AreaQo<T> extends BaseControllerQo<T> {




    private String type;

    private String name;


}
