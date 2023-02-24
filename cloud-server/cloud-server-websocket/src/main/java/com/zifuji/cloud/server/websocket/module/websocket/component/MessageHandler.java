package com.zifuji.cloud.server.websocket.module.websocket.component;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.MyWebsocketMessage;
import com.zifuji.cloud.base.bean.UserInfo;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class MessageHandler extends AbstractWebSocketHandler {

	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("afterConnectionEstablished");
		Object obj = session.getPrincipal();
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) obj;
		assert token != null;
		UserInfo userInfo = (UserInfo) token.getPrincipal();
		String redisKey = BaseConstant.REDIS_WEBSOCKET + userInfo.getId();
		stringRedisTemplate.opsForSet().add(redisKey, session.getId());
		WebsocketSessionManager.add(session.getId(), session);
		super.afterConnectionEstablished(session);
	}


	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		super.handleMessage(session, message);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		MyWebsocketMessage mes = JSONObject.parseObject(message.getPayload(), MyWebsocketMessage.class);
		MyWebsocketMessage myWebsocketMessage = new MyWebsocketMessage();
		switch (mes.getBusinessType()) {
		case "sendMessage":
			log.info(message.getPayload());
			myWebsocketMessage.setBusinessType("sendMessage");
			myWebsocketMessage.setObj(message.getPayload());
			message = new TextMessage(JSONObject.toJSONString(myWebsocketMessage));
			session.sendMessage(message);
			break;
		default:
			myWebsocketMessage.setBusinessType("heartbeat");
			myWebsocketMessage.setObj("h");
			message = new TextMessage(JSONObject.toJSONString(myWebsocketMessage));
			session.sendMessage(message);
			break;
		}

	}

	@Override
	protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
		super.handleBinaryMessage(session, message);
	}

	@Override
	protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
		super.handlePongMessage(session, message);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		super.handleTransportError(session, exception);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info(status.toString());
		Object obj = session.getPrincipal();
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) obj;
		assert token != null;
		UserInfo userInfo = (UserInfo) token.getPrincipal();
		String redisKey = BaseConstant.REDIS_WEBSOCKET + userInfo.getId();
		stringRedisTemplate.opsForSet().remove(redisKey, session.getId());
		WebsocketSessionManager.removeAndClose(session.getId());
		super.afterConnectionClosed(session, status);
	}

	@Override
	public boolean supportsPartialMessages() {
		return super.supportsPartialMessages();
	}

}
