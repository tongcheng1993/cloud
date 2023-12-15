package com.zifuji.cloud.server.sys.module.quartz.controller.bo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QuartzRecordComponentMo {

    private String name;

    private String description;

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String status;
}
