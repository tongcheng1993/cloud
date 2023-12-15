package com.zifuji.cloud.server.business.module.webUser.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class WebMenuVo extends BaseControllerVo {

    private String label;

    private String name;

    private String path;

    private String component;

    private Boolean showFlag;

    private String icon;
}
