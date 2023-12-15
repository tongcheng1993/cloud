package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserVo extends BaseControllerVo {

    private String userName;


}
