package com.zifuji.cloud.server.websocket.module.websocket.interceptor;


import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class UserSockJSTransportHandler extends DefaultHandshakeHandler {


    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        log.info("UserSockJSTransportHandler determineUser");
        Principal principal = request.getPrincipal();
        if (ObjectUtil.isNull(principal)) {
            principal = new AnonymousAuthenticationToken("websocket", "1", new ArrayList<>());
        } else {
            if (principal instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
                principal = new AnonymousAuthenticationToken("websocket", usernamePasswordAuthenticationToken.getPrincipal(), usernamePasswordAuthenticationToken.getAuthorities());
            }
        }
        return principal;
    }

    @Override
    public void start() {
        log.info("UserSockJSTransportHandler start");
        super.start();
    }

    @Override
    public void stop() {
        log.info("UserSockJSTransportHandler stop");
        super.stop();
    }

    @Override
    protected void doStop() {
        log.info("UserSockJSTransportHandler doStop");
        super.doStop();
    }

    @Override
    protected void doStart() {
        log.info("UserSockJSTransportHandler doStart");
        super.doStart();
    }
}
