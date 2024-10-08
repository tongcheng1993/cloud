package com.zifuji.cloud.server.manage.module.manageUser.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.zifuji.cloud.server.base.db.BaseMo;

@Data
@ApiModel(description = "")
public class UpdateManageUserMo extends BaseMo {

	private String userName;

	private String passWord;

	private String shortName;

}
