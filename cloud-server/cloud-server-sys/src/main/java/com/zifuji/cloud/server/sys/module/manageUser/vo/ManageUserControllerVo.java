package com.zifuji.cloud.server.sys.module.manageUser.vo;

import java.util.List;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserControllerVo extends BaseControllerVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String userName;

    private String passWord;

    private String name;

    private List<ManageRoleControllerVo> manageRoleVoList;

    private List<ManagePermissionControllerVo> managePermissionVoList;

    private List<ManageMenuControllerVo> manageMenuVoList;
}
