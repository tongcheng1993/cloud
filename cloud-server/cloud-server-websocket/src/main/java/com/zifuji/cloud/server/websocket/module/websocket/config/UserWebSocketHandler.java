package com.zifuji.cloud.server.websocket.module.websocket.config;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.server.base.object.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;


@Slf4j
public class UserWebSocketHandler extends WebSocketHandlerDecorator {

    private StringRedisTemplate stringRedisTemplate;

    public UserWebSocketHandler(WebSocketHandler delegate) {
        super(delegate);
    }

    public UserWebSocketHandler(WebSocketHandler delegate, StringRedisTemplate stringRedisTemplate) {
        super(delegate);
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        super.handleMessage(session, message);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
       super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String longId = stringRedisTemplate.opsForValue().get("ws" + session.getId());
        stringRedisTemplate.delete("ws" + session.getId());
        stringRedisTemplate.opsForSet().remove("ws" + longId, session.getId());
        super.afterConnectionClosed(session, closeStatus);
    }
}
