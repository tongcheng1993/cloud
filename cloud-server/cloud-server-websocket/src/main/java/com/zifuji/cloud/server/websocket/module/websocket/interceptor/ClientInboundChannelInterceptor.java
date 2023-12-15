package com.zifuji.cloud.server.websocket.module.websocket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;


@Slf4j
public class ClientInboundChannelInterceptor implements ChannelInterceptor {


    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor header = StompHeaderAccessor.wrap(message);
        if (header.getCommand() == null) return;
        log.info(header.getCommand()+"");
        if (header.getCommand().equals(StompCommand.CONNECT)) {

        }
        if (header.getCommand().equals(StompCommand.DISCONNECT)) {

        }
        log.info("postSend");
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {

        return message;
    }
}
