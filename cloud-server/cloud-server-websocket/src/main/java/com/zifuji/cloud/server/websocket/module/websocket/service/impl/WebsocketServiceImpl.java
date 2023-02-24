package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
<<<<<<< HEAD
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
=======
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.MyWebsocketMessage;
<<<<<<< HEAD
import com.zifuji.cloud.server.websocket.module.mq.service.MqService;
=======
import com.zifuji.cloud.base.exception.Exception300;
import com.zifuji.cloud.server.websocket.module.mq.service.MqService;
import com.zifuji.cloud.server.websocket.module.websocket.component.MessageHandler;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import com.zifuji.cloud.server.websocket.module.websocket.component.WebsocketSessionManager;
import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {

    private StringRedisTemplate stringRedisTemplate;

    private MqService mqService;

<<<<<<< HEAD
    private SimpMessagingTemplate simpMessagingTemplate;
=======
    @Override
    public void sendMessage(Long userId, String mes) {
        TextMessage textMessage = new TextMessage(mes);
        if (ObjectUtil.isNotNull(userId)) {
            String redisKey = BaseConstant.REDIS_WEBSOCKET + userId;
            List<String> valueList = stringRedisTemplate.opsForSet().pop(redisKey, stringRedisTemplate.opsForSet().size(redisKey));
            if (ObjectUtil.isEmpty(valueList)) {
                return;
            }
            for (int i = 0; i < valueList.size(); i++) {
                String sessionId = valueList.get(i);
                WebSocketSession session = WebsocketSessionManager.get(sessionId);
                if (ObjectUtil.isNotNull(session)) {
                    try {
                        session.sendMessage(textMessage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {

        }

    }

    @Override
    public void sendMessage(MyWebsocketMessage myWebsocketMessage) {
        sendMessage(myWebsocketMessage.getUserId(), JSONObject.toJSONString(myWebsocketMessage));
    }

    @Override
    public void sendMq(MyWebsocketMessage myWebsocketMessage) {
        mqService.sendFanoutMessage(JSONObject.toJSONString(myWebsocketMessage));
    }
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df

    @Override
    public void sendWsMessage(SendWsMessageMo sendWsMessageMo) {
        MyWebsocketMessage myWebsocketMessage = new MyWebsocketMessage();
        myWebsocketMessage.setBusinessType(sendWsMessageMo.getBusinessType());
        myWebsocketMessage.setUserId(sendWsMessageMo.getUserId());
        myWebsocketMessage.setObj(sendWsMessageMo.getObj());
        myWebsocketMessage.setTime(LocalDateTime.now());
<<<<<<< HEAD
        simpMessagingTemplate.convertAndSend("/topic/public","123123123");
=======
        this.sendMq(myWebsocketMessage);
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }

    @Override
    public Integer info() {
<<<<<<< HEAD
        return 0;
=======
        return WebsocketSessionManager.size();
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }

}
