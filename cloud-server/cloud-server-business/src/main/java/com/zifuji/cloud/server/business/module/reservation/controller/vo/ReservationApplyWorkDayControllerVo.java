package com.zifuji.cloud.server.business.module.reservation.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationTimeBo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyWorkDayControllerVo  {

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

    private String dayName;

    private String dayStatus;

    private int haveNum;

    private String reservationToken;

    private List<ReservationTimeBo> reservationTimeBoList;

}
