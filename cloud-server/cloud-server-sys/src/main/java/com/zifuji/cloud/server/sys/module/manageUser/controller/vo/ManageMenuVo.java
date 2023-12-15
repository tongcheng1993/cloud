package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuVo extends BaseControllerVo {

    private String label;

    private String name;

    private String path;

    private String component;

    private Boolean showFlag;

    private String icon;


}
