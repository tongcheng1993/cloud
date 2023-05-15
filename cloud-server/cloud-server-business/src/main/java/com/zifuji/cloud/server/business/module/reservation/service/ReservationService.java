package com.zifuji.cloud.server.business.module.reservation.service;

import com.zifuji.cloud.server.business.module.reservation.mo.ReservationSettingMainMo;
import com.zifuji.cloud.server.business.module.reservation.vo.ReservationSettingMainVo;

public interface ReservationService {

    ReservationSettingMainVo saveReservationSettingMain(ReservationSettingMainMo reservationSettingMainMo);

    ReservationSettingMainVo getDefaultReservationSetting();

    ReservationSettingMainVo getReservationSetting(String code);

}
