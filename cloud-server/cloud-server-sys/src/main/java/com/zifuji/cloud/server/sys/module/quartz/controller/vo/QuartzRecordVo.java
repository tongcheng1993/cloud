package com.zifuji.cloud.server.sys.module.quartz.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QuartzRecordVo extends BaseControllerVo {

    private String name;

    private String description;

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String status;


}
