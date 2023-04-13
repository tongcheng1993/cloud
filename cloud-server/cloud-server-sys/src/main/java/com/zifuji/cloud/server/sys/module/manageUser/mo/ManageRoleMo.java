package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageRoleMo extends BaseMo {

    // 角色名称
    private String name;
    // 角色编码
    private String code;
    // 描述
    private String description;

}
