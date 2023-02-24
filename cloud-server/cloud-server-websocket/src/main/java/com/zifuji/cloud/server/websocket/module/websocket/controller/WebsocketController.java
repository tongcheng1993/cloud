package com.zifuji.cloud.server.websocket.module.websocket.controller;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;

@Api(value = "")
@Slf4j
@RestController
@RequestMapping(value = "/websocket")
@AllArgsConstructor
public class WebsocketController {

    private WebsocketService websocketService;

    @ApiOperation(value = "发送消息")
    @PostMapping(value = "/sendWsMessage")
    public Result<Boolean> sendWsMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsMessage(sendWsMessageMo);
        return new Result<Boolean>().setObj(true);
    }

<<<<<<< HEAD
    @ApiOperation(value = "获取服务器信息")
    @GetMapping(value = "/info")
    public Result<Integer> info() {
        log.info(JSONObject.toJSONString("空参数"));
=======
    @PreAuthorize("hasAnyRole('admin')")
    @ApiOperation(value = "获取服务器信息")
    @GetMapping(value = "/info")
    public Result<Integer> info() {
        log.info(JSONObject.toJSONString(""));
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        Integer result = websocketService.info();
        log.info(JSONObject.toJSONString(result));
        return new Result<Integer>().setObj(result);
    }

}
