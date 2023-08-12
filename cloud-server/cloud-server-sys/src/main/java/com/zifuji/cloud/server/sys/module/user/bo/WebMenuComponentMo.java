package com.zifuji.cloud.server.sys.module.user.bo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebMenuComponentMo  {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long parentId;

    private Long sortNum;

    private String name;

    private String path;

    private String component;

    private Integer showFlag;

    private String iconFlag;


}
