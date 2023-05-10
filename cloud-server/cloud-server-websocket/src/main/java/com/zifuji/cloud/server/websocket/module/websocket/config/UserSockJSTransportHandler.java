package com.zifuji.cloud.server.websocket.module.websocket.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Slf4j
public class UserSockJSTransportHandler extends DefaultHandshakeHandler {


    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        return request.getPrincipal();
    }

    @Override
    public void start() {
        log.info("start");
        super.start();
    }

    @Override
    public void stop() {
        log.info("stop");
        super.stop();
    }

    @Override
    protected void doStop() {
        log.info("doStop");
        super.doStop();
    }

    @Override
    protected void doStart() {
        log.info("doStart");
        super.doStart();
    }
}
