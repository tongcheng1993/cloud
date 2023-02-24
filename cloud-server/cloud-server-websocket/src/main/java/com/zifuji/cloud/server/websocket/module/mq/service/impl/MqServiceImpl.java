package com.zifuji.cloud.server.websocket.module.mq.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.zifuji.cloud.server.websocket.module.mq.service.MqService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MqServiceImpl implements MqService {

	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendFanoutMessage(String mes) {
		rabbitTemplate.convertAndSend(mes);
	}

}
