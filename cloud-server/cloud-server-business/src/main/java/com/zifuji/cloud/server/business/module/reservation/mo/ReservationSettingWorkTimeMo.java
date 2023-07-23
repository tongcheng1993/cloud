package com.zifuji.cloud.server.business.module.reservation.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingWorkTimeMo {

    // 传入的末尾是00:00
    private String startRange;
    // 传入的末尾是59:59
    private String endRange;
    //  不能是复数
    private int allNum;

}
