package com.zifuji.cloud.server.business.module.reservation.vo;

import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingMainVo extends BaseVo {

    private String code;

    private Integer openDays;

    private String openAddr;

    private String description;

    private List<ReservationSettingWorkDayVo> reservationSettingWorkDayVoList;

}
