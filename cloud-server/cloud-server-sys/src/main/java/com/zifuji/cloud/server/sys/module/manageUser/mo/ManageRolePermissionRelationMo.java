package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageRolePermissionRelationMo extends BaseMo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @NotNull(message = "")
    private Long roleId;

    private List<Long> permissionIdList;
}
