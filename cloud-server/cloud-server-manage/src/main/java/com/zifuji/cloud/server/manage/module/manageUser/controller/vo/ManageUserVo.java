package com.zifuji.cloud.server.manage.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserVo extends BaseVo {

    private String userName;

	private String shortName;
}
