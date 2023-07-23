package com.zifuji.cloud.server.business.module.reservation.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationTimeBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyWorkDayControllerVo extends BaseControllerVo {

    private String dayName;

    private String dayStatus;

    private int haveNum;

    private String reservationToken;

    private List<ReservationTimeBo> reservationTimeBoList;

}
