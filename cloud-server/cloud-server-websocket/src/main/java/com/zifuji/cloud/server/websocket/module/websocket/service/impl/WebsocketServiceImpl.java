package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import com.alibaba.fastjson.JSONObject;


import com.zifuji.cloud.server.websocket.module.websocket.controller.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {


    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public Boolean sendWsAllMessage(SendWsMessageMo sendWsMessageMo) {
        simpMessagingTemplate.convertAndSend("/topic/public", sendWsMessageMo);
        return true;
    }

    @Override
    public Boolean sendWsMessage(SendWsMessageMo sendWsMessageMo) {
        simpMessagingTemplate.convertAndSendToUser(sendWsMessageMo.getUserId(), "/topic/chat", JSONObject.toJSONString(sendWsMessageMo));
        return true;
    }

    @Override
    public Integer info() {
        return 0;
    }

}
