package com.zifuji.cloud.server.websocket.module.websocket.config;


import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.util.SecurityUtil;
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
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    protected void doStop() {
        super.doStop();
    }

    @Override
    protected void doStart() {
        super.doStart();
    }
}
