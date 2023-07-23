package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "")
public class SaveNameControllerMo extends BaseControllerMo {

    @NotBlank(message = "新昵称不能为空")
    private String name;
}
