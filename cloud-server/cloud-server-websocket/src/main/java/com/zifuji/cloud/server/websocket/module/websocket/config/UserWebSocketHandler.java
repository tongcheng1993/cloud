package com.zifuji.cloud.server.websocket.module.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.*;
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
        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof BinaryMessage) {
            this.handleBinaryMessage(session, (BinaryMessage) message);
        } else {
            if (!(message instanceof PongMessage)) {
                throw new IllegalStateException("Unexpected WebSocket message type: " + message);
            }

            this.handlePongMessage(session, (PongMessage) message);
        }
        super.handleMessage(session, message);
    }

    private void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    }

    private void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
    }

    private void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        super.afterConnectionClosed(session, closeStatus);
    }
}
