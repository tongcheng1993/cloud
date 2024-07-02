package com.zifuji.cloud.server.business.module.reservation.service;

import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationSettingMainControllerMo;
import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationSettingSpeDayMo;
import com.zifuji.cloud.server.business.module.reservation.controller.vo.ReservationApplyMainControllerVo;
import com.zifuji.cloud.server.business.module.reservation.controller.vo.ReservationApplyWorkDayControllerVo;

public interface ReservationService {

    ReservationApplyMainControllerVo saveReservationSettingMain(ReservationSettingMainControllerMo reservationSettingMainMo);

    Boolean saveSpeDayAndTime(ReservationSettingSpeDayMo reservationSettingSpeDayMo);

    Boolean openReservation(ReservationSettingMainControllerMo reservationSettingMainMo);

    ReservationApplyMainControllerVo getDefaultReservationApplyWorkDaySetting();

    ReservationApplyMainControllerVo getReservationApplyWorkDaySetting(String code);

    ReservationApplyWorkDayControllerVo getDefaultReservationApplyWorkTimeSetting(String day);

    ReservationApplyWorkDayControllerVo getReservationApplyWorkTimeSetting(String code, String day);
}
