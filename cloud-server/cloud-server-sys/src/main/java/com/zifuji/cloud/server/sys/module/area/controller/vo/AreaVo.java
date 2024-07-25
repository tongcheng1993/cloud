package com.zifuji.cloud.server.sys.module.area.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class AreaVo extends BaseVo {

    private String type;

    private String name;

    private String realName;

    private String code;

    private List<AreaVo> children;


}
