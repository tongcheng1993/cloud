package com.zifuji.cloud.server.business.module.reservation.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationDayBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyMainControllerVo {
    @ApiModelProperty("")
    private String id;
    @ApiModelProperty("")
    private String createBy;
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
    private String code;

    private Integer openDays;

    private String openAddr;

    private String description;

    private List<ReservationDayBo> list;

}
