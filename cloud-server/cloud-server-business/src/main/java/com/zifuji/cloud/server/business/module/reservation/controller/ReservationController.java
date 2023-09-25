package com.zifuji.cloud.server.business.module.reservation.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.reservation.controller.mo.ReservationApplyMo;
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

@Slf4j
@Api(value = "预约/应用")
@RestController
@RequestMapping(value = "/reservation/apply")
@AllArgsConstructor
public class ReservationApplyController {

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


}
