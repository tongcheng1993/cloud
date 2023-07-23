package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ResetPassWordControllerMo extends BaseControllerMo {
    @NotNull(message = "")
    private Long id;
    @NotBlank(message = "")
    private String passWord;

}
