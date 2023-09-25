package com.zifuji.cloud.server.sys.module.manageUser.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class GetMenuControllerVo {
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createBy;
    @ApiModelProperty("")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updateBy;
    @ApiModelProperty("")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long sortNum;
    @ApiModelProperty("")
    private Boolean delFlag;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    @ApiModelProperty("")
    private String name;
    @ApiModelProperty("")
    private String path;
    @ApiModelProperty("")
    private String component;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer showFlag;
    @ApiModelProperty("")
    private String iconFlag;
}
