package com.zifuji.cloud.server.business.module.blog.controller.mo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotBlank;


@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveBlogMo  {

    @ApiModelProperty("")
    private String id;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String blogName;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String blogType;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String blogContent;
    @ApiModelProperty("")
    @NotBlank(message = "")
    private String blogText;

}
