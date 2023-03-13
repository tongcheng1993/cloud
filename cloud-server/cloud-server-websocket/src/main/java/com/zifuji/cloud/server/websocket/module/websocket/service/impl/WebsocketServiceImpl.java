package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import java.time.LocalDateTime;

import org.springframework.data.redis.core.StringRedisTemplate;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.base.bean.MyWebsocketMessage;

import com.zifuji.cloud.server.websocket.module.mq.service.MqService;
import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {

    private StringRedisTemplate stringRedisTemplate;

    private MqService mqService;


    private SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public void sendWsMessage(SendWsMessageMo sendWsMessageMo) {
        MyWebsocketMessage myWebsocketMessage = new MyWebsocketMessage();
        myWebsocketMessage.setBusinessType(sendWsMessageMo.getBusinessType());
        myWebsocketMessage.setUserId(sendWsMessageMo.getUserId());
        myWebsocketMessage.setObj(sendWsMessageMo.getObj());
        myWebsocketMessage.setTime(LocalDateTime.now());

        simpMessagingTemplate.convertAndSend("/topic/public","123123123");

    }

    @Override
    public Integer info() {

        return 0;

    }

}
