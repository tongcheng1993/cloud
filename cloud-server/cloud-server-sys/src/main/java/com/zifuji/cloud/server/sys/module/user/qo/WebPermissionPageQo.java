package com.zifuji.cloud.server.sys.module.user.qo;

import com.zifuji.cloud.base.bean.BasePageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebPermissionPageQo extends BasePageQo {

    private List<Long> userIds;

    private List<String> userNames;

    private List<Long> roleIds;

    private List<String> roleNames;

    private List<String> roleCodes;


}
