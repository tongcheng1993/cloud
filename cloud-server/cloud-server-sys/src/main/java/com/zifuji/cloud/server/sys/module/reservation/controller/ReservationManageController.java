package com.zifuji.cloud.server.sys.module.reservation.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.reservation.mo.ReservationSettingMainMo;
import com.zifuji.cloud.server.sys.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.sys.module.reservation.vo.ReservationSettingMainVo;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public Result<ReservationSettingMainVo> saveReservationSettingMain(@RequestBody @Valid ReservationSettingMainMo reservationSettingMainMo) {


        ReservationSettingMainVo result = reservationService.saveReservationSettingMain(reservationSettingMainMo);

        return new Result<ReservationSettingMainVo>().setObj(result);
    }



}
