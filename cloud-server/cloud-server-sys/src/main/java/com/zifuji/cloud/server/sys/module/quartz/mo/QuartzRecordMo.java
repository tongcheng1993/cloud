package com.zifuji.cloud.server.sys.module.quartz.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QuartzRecordMo extends BaseMo {

    private Long id;

    private String name;

    private String description;

    @NotBlank(message = "类名不能为空")
    private String jobClassName;

    @NotBlank(message = "任务组名不能为空")
    private String jobGroupName;

    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;

    private String status;
}
