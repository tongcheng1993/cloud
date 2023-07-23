package com.zifuji.cloud.server.sys.module.manageUser.bo;

import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageMenuComponentMo extends BaseComponentMo {


    private Long parentId;

    private Long id;


    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;


}