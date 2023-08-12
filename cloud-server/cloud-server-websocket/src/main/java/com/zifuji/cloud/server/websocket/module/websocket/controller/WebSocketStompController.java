package com.zifuji.cloud.server.websocket.module.websocket.controller;

import com.zifuji.cloud.base.bean.Result;

import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
public class WebSocketStompController {

    private WebsocketService websocketService;


    @ApiOperation(value = "发送消息")
    @MessageMapping(value = "/sendWsMessage")
    @SendTo(value = "/topic/public")
    public Result<Boolean> sendWsMessage(SendWsMessageMo sendWsMessageMo) {
        websocketService.sendWsMessage(sendWsMessageMo);
        return Result.setObj(true);
    }
}
