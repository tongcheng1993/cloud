package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;


import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {

    private StringRedisTemplate stringRedisTemplate;

    private RabbitMessagingTemplate rabbitMessagingTemplate;


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
