package com.zifuji.cloud.server.websocket.module.websocket.config;



import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    RabbitProperties rabbitProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app/");
        registry.setUserDestinationPrefix("/user/");
        registry.enableStompBrokerRelay("/queue/", "/topic/")
                .setRelayHost(rabbitProperties.getHost())
                .setClientLogin(rabbitProperties.getUsername())
                .setClientPasscode(rabbitProperties.getPassword())
                .setSystemLogin(rabbitProperties.getUsername())
                .setSystemPasscode(rabbitProperties.getPassword())
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(5000);


    }


}
