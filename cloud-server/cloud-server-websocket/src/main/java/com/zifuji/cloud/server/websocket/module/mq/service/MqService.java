package com.zifuji.cloud.server.websocket.module.mq.service;

public interface MqService {
	void sendFanoutMessage(String mes);
}
