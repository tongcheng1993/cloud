package com.zifuji.cloud.server.sys.module.manageUser.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String userName;

    private String passWord;

    private String name;

    private List<ManageRoleVo> manageRoleVoList;

    private List<ManagePermissionVo> managePermissionVoList;

    private List<ManageMenuVo> manageMenuVoList;
}
