package com.zifuji.cloud.server.websocket.module.websocket.service;



import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;

public interface WebsocketService {



	void sendWsMessage(SendWsMessageMo sendWsMessageMo);
	
	Integer info();
}
