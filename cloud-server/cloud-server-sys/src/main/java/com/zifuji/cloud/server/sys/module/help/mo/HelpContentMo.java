package com.zifuji.cloud.server.sys.module.help.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class HelpContentMo extends BaseMo {
    @NotBlank(message = "")
    private String content;
    @NotBlank(message = "")
    private String name = "help";
}
