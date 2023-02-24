package com.zifuji.cloud.server.websocket.module.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.socket.server.support.OriginHandshakeInterceptor;

import com.zifuji.cloud.server.websocket.module.websocket.component.MessageHandler;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer{
	
	private MessageHandler messageHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		DefaultHandshakeHandler defaultHandshakeHandler=new DefaultHandshakeHandler();
		OriginHandshakeInterceptor originHandshakeInterceptor = new OriginHandshakeInterceptor();
		HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor = new HttpSessionHandshakeInterceptor();
		registry.addHandler(messageHandler, "/ws")
//				.addInterceptors(originHandshakeInterceptor)
//				.addInterceptors(httpSessionHandshakeInterceptor)
//				.setHandshakeHandler(defaultHandshakeHandler)
				.setAllowedOrigins("*")
//				.withSockJS()
		;

	}

}
