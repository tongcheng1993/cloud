package com.zifuji.cloud.server.sys.module.quartz.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class UpdateCornControllerMo  {
    @NotNull(message = "id不能为空")
    private String id;
    @NotBlank(message = "cron表达式不能为空")
    private String cronExpression;
}
