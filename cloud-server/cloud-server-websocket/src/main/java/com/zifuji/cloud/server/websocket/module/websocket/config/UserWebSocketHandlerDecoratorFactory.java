package com.zifuji.cloud.server.websocket.module.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

@Slf4j
public class UserWebSocketHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    private StringRedisTemplate stringRedisTemplate;

    public UserWebSocketHandlerDecoratorFactory(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public WebSocketHandler decorate(WebSocketHandler webSocketHandler) {
        return new UserWebSocketHandler(webSocketHandler,stringRedisTemplate);
    }
}
