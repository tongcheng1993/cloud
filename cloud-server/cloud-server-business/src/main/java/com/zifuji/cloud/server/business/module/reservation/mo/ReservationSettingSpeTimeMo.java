package com.zifuji.cloud.server.business.module.reservation.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingSpeTimeMo {

    private LocalDate speDay;

    private String speStartRange;

    private String speEndRange;

    private int speAllNum;
}
