package com.zifuji.cloud.server.websocket.module.websocket.service;

<<<<<<< HEAD
=======
import com.zifuji.cloud.base.bean.MyWebsocketMessage;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;

public interface WebsocketService {

<<<<<<< HEAD
=======
	void sendMessage(Long userId, String mes);

	void sendMessage(MyWebsocketMessage myWebsocketMessage);

	void sendMq(MyWebsocketMessage myWebsocketMessage);
	
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
	void sendWsMessage(SendWsMessageMo sendWsMessageMo);
	
	Integer info();
}
