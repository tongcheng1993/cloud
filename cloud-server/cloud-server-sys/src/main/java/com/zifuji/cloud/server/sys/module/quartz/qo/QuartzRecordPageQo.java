package com.zifuji.cloud.server.sys.module.quartz.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QuartzRecordPageQo extends BaseControllerPageQo {

    private String name;
}
