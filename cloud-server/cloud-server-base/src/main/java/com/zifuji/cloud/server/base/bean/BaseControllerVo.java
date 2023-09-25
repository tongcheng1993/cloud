package com.zifuji.cloud.server.base.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseControllerVo {

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
    private Integer sortNum;
    @ApiModelProperty("")
    private Boolean delFlag;
    @ApiModelProperty("")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;

}
