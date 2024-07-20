package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(description = "")
public class AddManageUserMo {

    @NotBlank(message = "")
    private String userName;
    @NotBlank(message = "")
    private String shortName;

    private String passWord;
}
