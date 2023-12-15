package com.zifuji.cloud.server.sys.module.quartz.controller.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ResetQuartzRecordMo {
    @NotNull(message = "id不能为空")
    private String id;
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
    @NotNull(message = "定时任务状态不能为空")
    private Boolean status;
}
