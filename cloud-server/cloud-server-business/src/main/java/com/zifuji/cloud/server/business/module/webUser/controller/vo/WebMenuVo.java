package com.zifuji.cloud.server.business.module.webUser.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class WebMenuVo extends BaseVo {

    private String label;

    private String name;

    private String path;

    private String component;

    private Boolean showFlag;

    private String icon;
}
