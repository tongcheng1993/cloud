package com.zifuji.cloud.server.sys.module.manageUser.qo;

import java.util.List;

import com.zifuji.cloud.base.bean.BasePageQo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserPageQo extends BasePageQo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String userName;

    private String name;

    private List<String> roleName;

    private List<String> roleCode;
}
