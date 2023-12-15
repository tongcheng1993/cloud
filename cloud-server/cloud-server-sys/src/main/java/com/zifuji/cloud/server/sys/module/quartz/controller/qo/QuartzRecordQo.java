package com.zifuji.cloud.server.sys.module.quartz.controller.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QuartzRecordQo<T> extends BaseControllerQo<T> {

    private String name;
}
