package com.zifuji.cloud.server.sys.module.quartz.controller.vo;

import com.zifuji.cloud.server.base.db.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class JobDetailVo extends BaseControllerVo {

    private String jobGroupName;

    private String jobKey;

    private String jobDescription;

}
