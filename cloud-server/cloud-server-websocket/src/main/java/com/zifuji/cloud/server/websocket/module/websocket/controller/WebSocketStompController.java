package com.zifuji.cloud.server.websocket.module.websocket.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@AllArgsConstructor
public class WebSocketStompController {


}
