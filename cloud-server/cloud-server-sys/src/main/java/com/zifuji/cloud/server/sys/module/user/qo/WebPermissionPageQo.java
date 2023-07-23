package com.zifuji.cloud.server.sys.module.user.qo;

import com.zifuji.cloud.base.bean.controller.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebPermissionPageQo extends BaseControllerPageQo {

    private List<Long> userIds;

    private List<String> userNames;

    private List<Long> roleIds;

    private List<String> roleNames;

    private List<String> roleCodes;


}
