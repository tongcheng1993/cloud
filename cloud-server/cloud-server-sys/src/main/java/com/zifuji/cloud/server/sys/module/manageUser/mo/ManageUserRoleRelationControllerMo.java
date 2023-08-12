package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserRoleRelationControllerMo extends BaseControllerMo {

    private Long userId;

    private List<Long> roleIdList;
}
