package com.zifuji.cloud.server.sys.module.reservation.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.reservation.service.ReservationService;
import com.zifuji.cloud.server.sys.module.reservation.vo.ReservationSettingMainVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "reservation_apply")
@RestController
@RequestMapping(value = "/reservation/apply")
@AllArgsConstructor
public class ReservationApplyController {

    private ReservationService reservationService;

    @ApiOperation(value = "获取默认的预约日历")
    @PostMapping(value = "/getDefaultReservationSetting")
    public Result<ReservationSettingMainVo> getDefaultReservationSetting() {
        log.info("getDefaultReservationSetting");
        log.info(JSONObject.toJSONString("空参数"));
        ReservationSettingMainVo result = reservationService.getDefaultReservationSetting();
        log.info(JSONObject.toJSONString(result));
        return new Result<ReservationSettingMainVo>().setObj(result);
    }


}
