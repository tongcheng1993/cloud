package com.zifuji.cloud.server.business.module.friend.blog.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class BlogControllerVo extends BaseControllerVo {

    private String blogName;

    private String blogAuth;

    private String blogType;

    private String blogContent;

    private String blogText;


}
