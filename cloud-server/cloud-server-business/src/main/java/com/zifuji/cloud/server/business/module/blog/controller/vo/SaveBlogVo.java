package com.zifuji.cloud.server.business.module.blog.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveBlogVo  {
    @ApiModelProperty("")
    private Long id;
    @ApiModelProperty("")
    private Long createBy;
    @ApiModelProperty("")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty("")
    private String updateBy;
    @ApiModelProperty("")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @ApiModelProperty("")
    private String sortNum;
    @ApiModelProperty("")
    private Boolean delFlag;
    @ApiModelProperty("")
    private String parentId;
    private String blogName;

    private String blogAuth;

    private String blogType;

    private String blogContent;

    private String blogText;


}
