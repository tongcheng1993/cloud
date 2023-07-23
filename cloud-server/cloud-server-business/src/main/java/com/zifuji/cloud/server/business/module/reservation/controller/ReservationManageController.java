package com.zifuji.cloud.server.business.module.reservation.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.reservation.mo.ReservationSettingMainControllerMo;
import com.zifuji.cloud.server.business.module.reservation.mo.ReservationSettingSpeDayMo;
import com.zifuji.cloud.server.business.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.business.module.reservation.vo.ReservationApplyMainControllerVo;
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
@Api(value = "预约/管理")
@RestController
@RequestMapping(value = "/reservation/manage")
@AllArgsConstructor
public class ReservationManageController {

    private ReservationService reservationService;

    @ApiOperation(value = "保存基础预约配置")
    @PostMapping(value = "/saveReservationSettingMain")
    public Result<ReservationApplyMainControllerVo> saveReservationSettingMain(@RequestBody @Valid ReservationSettingMainControllerMo reservationSettingMainMo) {
        ReservationApplyMainControllerVo result = reservationService.saveReservationSettingMain(reservationSettingMainMo);
        return new Result<ReservationApplyMainControllerVo>().setObj(result);
    }

    @ApiOperation(value = "配置特殊日特殊时间设置")
    @PostMapping(value = "/saveSpeDayAndTime")
    public Result<Boolean> saveSpeDayAndTime(@RequestBody @Valid ReservationSettingSpeDayMo reservationSettingSpeDayMo) {
        return new Result<Boolean>().setObj(reservationService.saveSpeDayAndTime(reservationSettingSpeDayMo));
    }

    @ApiOperation(value = "开启预约")
    @PostMapping(value = "/openReservation")
    public Result<Boolean> openReservation(@RequestBody @Valid ReservationSettingMainControllerMo reservationSettingMainMo) {
        return new Result<Boolean>().setObj(reservationService.openReservation(reservationSettingMainMo));
    }
}
