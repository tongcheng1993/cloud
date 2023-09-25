package com.zifuji.cloud.server.business.module.reservation.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationApplyMo;
import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationSettingMainControllerMo;
import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationSettingSpeDayMo;
import com.zifuji.cloud.server.business.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.business.module.reservation.controller.vo.ReservationApplyMainControllerVo;
import com.zifuji.cloud.server.business.module.reservation.controller.vo.ReservationApplyWorkDayControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@Api(tags = "预约应用控制器")
@RestController
@RequestMapping(value = "/reservation")
@AllArgsConstructor
public class ReservationController {

    private ReservationService reservationService;

    @ApiOperation(value = "获取预约日历")
    @PostMapping(value = "/getReservationApplyWorkDaySetting")
    public Result<ReservationApplyMainControllerVo> getReservationApplyWorkDaySetting(@RequestBody ReservationApplyMo reservationApplyMo) {
        ReservationApplyMainControllerVo result = reservationService.getReservationApplyWorkDaySetting(reservationApplyMo.getCode());
        return Result.setObj(result);
    }

    @ApiOperation(value = "获取预约时间表")
    @PostMapping(value = "/getReservationApplyWorkTimeSetting")
    public Result<ReservationApplyWorkDayControllerVo> getReservationApplyWorkTimeSetting(@RequestBody ReservationApplyMo reservationApplyMo) {
        ReservationApplyWorkDayControllerVo vo = reservationService.getReservationApplyWorkTimeSetting(reservationApplyMo.getCode(), reservationApplyMo.getDayName());
        return Result.setObj(vo);
    }

    @ApiOperation(value = "保存基础预约配置")
    @PostMapping(value = "/saveReservationSettingMain")
    public Result<ReservationApplyMainControllerVo> saveReservationSettingMain(@RequestBody @Valid ReservationSettingMainControllerMo reservationSettingMainMo) {
        ReservationApplyMainControllerVo result = reservationService.saveReservationSettingMain(reservationSettingMainMo);
        return Result.setObj(result);
    }

    @ApiOperation(value = "配置特殊日特殊时间设置")
    @PostMapping(value = "/saveSpeDayAndTime")
    public Result<Boolean> saveSpeDayAndTime(@RequestBody @Valid ReservationSettingSpeDayMo reservationSettingSpeDayMo) {
        return Result.setObj(reservationService.saveSpeDayAndTime(reservationSettingSpeDayMo));
    }

    @ApiOperation(value = "开启预约")
    @PostMapping(value = "/openReservation")
    public Result<Boolean> openReservation(@RequestBody @Valid ReservationSettingMainControllerMo reservationSettingMainMo) {
        return Result.setObj(reservationService.openReservation(reservationSettingMainMo));
    }

}
