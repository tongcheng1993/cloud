package com.zifuji.cloud.server.websocket.module.websocket.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


@Slf4j
public class MyClientChannelInterceptor implements ChannelInterceptor {

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

        StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (ObjectUtil.isNotNull(stompHeaderAccessor)) {
            log.info("postSend:" + JSONObject.toJSONString(stompHeaderAccessor));
            StompCommand command = stompHeaderAccessor.getCommand();
            if (ObjectUtil.isNotNull(command)) {
                if (StompCommand.SEND.equals(command)) {
//                    UsernamePasswordAuthenticationToken token = stompHeaderAccessor.getMessageHeaders().get("simpUser", UsernamePasswordAuthenticationToken.class);
//                    UserInfo userInfo = (UserInfo) token.getDetails();
//                    SecurityUtil.setUserDetails(userInfo);
                }

            }

        }


    }
}
