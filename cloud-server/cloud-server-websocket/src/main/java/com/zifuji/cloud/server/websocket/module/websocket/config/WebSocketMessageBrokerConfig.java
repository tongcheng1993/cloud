package com.zifuji.cloud.server.websocket.module.websocket.config;


import com.zifuji.cloud.server.websocket.module.websocket.interceptor.ClientInboundChannelInterceptor;
import com.zifuji.cloud.server.websocket.module.websocket.interceptor.ClientOutboundChannelInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@AllArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    private RabbitProperties rabbitProperties;

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        StompWebSocketEndpointRegistration stompWebSocketEndpointRegistration = registry.addEndpoint("/ws");
        stompWebSocketEndpointRegistration.setAllowedOrigins("*");
//        stompWebSocketEndpointRegistration.addInterceptors(new UserHandshakeInterceptor(stringRedisTemplate));
//        stompWebSocketEndpointRegistration.setHandshakeHandler(new UserSockJSTransportHandler());
        stompWebSocketEndpointRegistration.withSockJS();


    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
//        registry.addDecoratorFactory(new UserWebSocketHandlerDecoratorFactory(stringRedisTemplate))
//                .setMessageSizeLimit(64 * 1024)
//                .setSendBufferSizeLimit(1024 * 1024 * 10)
//                .setSendTimeLimit(1000 * 10 * 6);
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ClientInboundChannelInterceptor());
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
//        registration.interceptors(new ClientOutboundChannelInterceptor());
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

//        // 前端绑定个人通道
//        registry.setUserDestinationPrefix("/user/");
//        // 前端使用ws发送消息的时候
//        registry.setApplicationDestinationPrefixes("/app/");
//        // 简单cache广播
//        registry.enableSimpleBroker("/topic", "/queue", "/user");


        // 前端绑定个人通道
        registry.setUserDestinationPrefix("/user");
        // 前端使用ws发送消息的时候
        registry.setApplicationDestinationPrefixes("/app");
        // 前端绑定stomp广播路径
        registry.enableStompBrokerRelay("/exchange", "/topic", "/queue", "/amq/queue")
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
