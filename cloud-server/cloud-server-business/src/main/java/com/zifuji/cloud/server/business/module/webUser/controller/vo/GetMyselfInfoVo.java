package com.zifuji.cloud.server.business.module.webUser.controller.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class GetMyselfInfoVo {
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
    private String userName;


}
