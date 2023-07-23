package com.zifuji.cloud.server.business.module.reservation.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingWorkDayMo {

    // 1 星期天 2 星期一 3 星期二 4 星期三 5 星期四 6 星期五 7 星期六
    private Integer dayNo;

    private String workFlag;

}
