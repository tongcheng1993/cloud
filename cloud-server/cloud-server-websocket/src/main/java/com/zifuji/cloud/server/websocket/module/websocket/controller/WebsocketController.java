package com.zifuji.cloud.server.websocket.module.websocket.controller;

import com.zifuji.cloud.base.bean.Result;

import com.zifuji.cloud.server.websocket.module.websocket.controller.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(value = "消息控制器")
@Slf4j
@RestController
@RequestMapping(value = "/websocket")
@AllArgsConstructor
public class WebsocketController {

    private WebsocketService websocketService;

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "获取服务器信息")
    @PostMapping(value = "/info")
    @PreAuthorize("hasAnyAuthority('ROLE_register')")
    public Result<Integer> info() {
        Integer result = websocketService.info();
        return Result.setObj(result);
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "发送全网站通知")
    @PostMapping(value = "/sendWsAllMessage")
    @PreAuthorize("hasAnyAuthority('ROLE_register')")
    public Result<Boolean> sendWsAllMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsAllMessage(sendWsMessageMo);
        return Result.setObj(true);
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "发送个人消息")
    @PostMapping(value = "/sendWsOneMessage")
    @PreAuthorize("hasAnyAuthority('ROLE_register')")
    public Result<Boolean> sendWsOneMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsOneMessage(sendWsMessageMo);
        return Result.setObj(true);
    }


}
