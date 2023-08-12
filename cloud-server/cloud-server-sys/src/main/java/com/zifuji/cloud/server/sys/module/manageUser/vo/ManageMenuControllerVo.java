package com.zifuji.cloud.server.sys.module.manageUser.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuControllerVo extends BaseControllerVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;

    private List<ManageMenuControllerVo> children;
}
