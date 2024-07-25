package com.zifuji.cloud.server.manage.module.manageUser.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuVo extends BaseVo {

    private String label;

    private String name;

    private String path;

    private String component;

    private Boolean showFlag;

    private String icon;


}
