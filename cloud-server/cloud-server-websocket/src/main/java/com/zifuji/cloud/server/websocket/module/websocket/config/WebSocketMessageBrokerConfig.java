package com.zifuji.cloud.server.websocket.module.websocket.config;


import com.zifuji.cloud.server.websocket.module.websocket.interceptor.MyClientChannelInterceptor;
import com.zifuji.cloud.server.websocket.module.websocket.interceptor.MyHandshakeInterceptor;
import com.zifuji.cloud.server.websocket.module.websocket.interceptor.MyTransportHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Slf4j
@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    private RabbitProperties rabbitProperties;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration stompWebSocketEndpointRegistration = registry.addEndpoint("/ws");
        stompWebSocketEndpointRegistration.setAllowedOrigins("*");
        stompWebSocketEndpointRegistration.addInterceptors(new MyHandshakeInterceptor());
        stompWebSocketEndpointRegistration.setHandshakeHandler(new MyTransportHandler());
        stompWebSocketEndpointRegistration.withSockJS();
    }


    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyClientChannelInterceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(new MyClientChannelInterceptor());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        // 前端绑定个人通道
        registry.setUserDestinationPrefix("/user");
        // 前端使用ws发送消息的时候
        registry.setApplicationDestinationPrefixes("/app");
        // 前端绑定stomp广播路径
        registry.enableStompBrokerRelay("/topic")
                .setVirtualHost(rabbitProperties.getVirtualHost())
                .setRelayHost(rabbitProperties.getHost())
                .setClientLogin(rabbitProperties.getUsername())
                .setClientPasscode(rabbitProperties.getPassword())
                .setSystemLogin(rabbitProperties.getUsername())
                .setSystemPasscode(rabbitProperties.getPassword())
                .setSystemHeartbeatSendInterval(5000)
                .setSystemHeartbeatReceiveInterval(5000);

    }

}
