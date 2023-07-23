package com.zifuji.cloud.server.business.module.reservation.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingSpeDayMo {

    private LocalDate speDay;

    private String workFlag;

    private List<ReservationSettingSpeTimeMo> reservationSettingSpeTimeMoList;
}
