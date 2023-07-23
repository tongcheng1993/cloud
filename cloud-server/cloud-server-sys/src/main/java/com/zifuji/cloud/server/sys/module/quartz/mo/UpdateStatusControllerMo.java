package com.zifuji.cloud.server.sys.module.quartz.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class UpdateStatusControllerMo extends BaseControllerMo {
    @NotNull(message = "id不能为空")
    private Long id;
    @NotBlank(message = "状态不能为空")
    private String status;
}
