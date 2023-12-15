package com.zifuji.cloud.server.sys.module.quartz.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AddQuartzRecordMo {
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "描述不能为空")
    private String description;
    @NotBlank(message = "类名不能为空")
    private String jobClassName;
    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
}
