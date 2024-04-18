package com.zifuji.cloud.server.websocket.module.websocket.controller;

import com.zifuji.cloud.base.bean.Result;

import com.zifuji.cloud.server.websocket.module.websocket.controller.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class WebSocketStompController {

    private WebsocketService websocketService;


    @ApiOperation(value = "发送消息")
    @MessageMapping(value = "/sendWsMessage")
    public Result<Boolean> sendWsMessage(@Payload SendWsMessageMo sendWsMessageMo, Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        websocketService.sendWsMessage(sendWsMessageMo);
        return Result.setObj(true);
    }
}
