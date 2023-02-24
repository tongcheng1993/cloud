package com.zifuji.cloud.server.sys.module.dashboard.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.server.sys.module.dashboard.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.base.bean.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "dashboard")
@RestController
@RequestMapping(value = "/dashboard")
@AllArgsConstructor
public class DashboardController {

    private DashboardService dashboardService;

    @ApiOperation(value = "")
    @GetMapping(value = "/info")
    public Result<String> info() {
        log.info(JSONObject.toJSONString("空参数"));
        String result = dashboardService.info();
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "")
    @GetMapping(value = "/getParameter")
    public Result<String> getParameter() {
        log.info(JSONObject.toJSONString("空参数"));
        String result = dashboardService.getParameter();
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


}