package com.zifuji.cloud.server.business.module.reservation.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyMo {


    private String code;

    private String dayName;


}
