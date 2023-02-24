package com.zifuji.cloud.server.sys.module.manageUser.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;

    private List<ManageMenuVo> children;
}
