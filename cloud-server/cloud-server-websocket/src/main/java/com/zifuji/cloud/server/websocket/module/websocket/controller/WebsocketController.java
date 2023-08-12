package com.zifuji.cloud.server.websocket.module.websocket.controller;

import com.zifuji.cloud.base.bean.Result;

import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(value = "")
@Slf4j
@RestController
@RequestMapping(value = "/websocket")
@AllArgsConstructor
public class WebsocketController {

    private WebsocketService websocketService;


    @ApiOperation(value = "获取服务器信息")
    @PostMapping(value = "/info")
    public Result<Integer> info() {
        Integer result = websocketService.info();
        return Result.setObj(result);
    }

    @ApiOperation(value = "发送全网站通知")
    @PostMapping(value = "/sendWsAllMessage")
    public Result<Boolean> sendWsAllMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsAllMessage(sendWsMessageMo);
        return Result.setObj(true);
    }

    @ApiOperation(value = "发送个人消息")
    @PostMapping(value = "/sendWsOneMessage")
    public Result<Boolean> sendWsOneMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsMessage(sendWsMessageMo);
        return Result.setObj(true);
    }


}
