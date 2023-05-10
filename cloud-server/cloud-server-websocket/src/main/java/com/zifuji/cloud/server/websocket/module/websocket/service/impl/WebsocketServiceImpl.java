package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.MyWebsocketMessage;

import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {

    private StringRedisTemplate stringRedisTemplate;

    private SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public void sendWsAllMessage(SendWsMessageMo sendWsMessageMo) {
        simpMessagingTemplate.convertAndSend("/topic/public", sendWsMessageMo);
    }

    @Override
    public void sendWsMessage(SendWsMessageMo sendWsMessageMo) {
        Set<String> set = stringRedisTemplate.opsForSet().members("ws" + sendWsMessageMo.getUserId());
        if (ObjectUtil.isNotNull(set) && ObjectUtil.isNotEmpty(set)) {
            for (String str : set) {
                simpMessagingTemplate.convertAndSendToUser(str, "/message", JSONObject.toJSONString(sendWsMessageMo));
            }
        }
        MyWebsocketMessage myWebsocketMessage = new MyWebsocketMessage();
        myWebsocketMessage.setBusinessType(sendWsMessageMo.getBusinessType());
        myWebsocketMessage.setUserId(sendWsMessageMo.getUserId());
        myWebsocketMessage.setObj(sendWsMessageMo.getObj());
        myWebsocketMessage.setTime(LocalDateTime.now());
    }

    @Override
    public Integer info() {

        return 0;

    }

}
