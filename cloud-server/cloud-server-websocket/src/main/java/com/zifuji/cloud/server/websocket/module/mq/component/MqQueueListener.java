package com.zifuji.cloud.server.websocket.module.mq.component;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.MyWebsocketMessage;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MqQueueListener {
	
	private WebsocketService websocketService;

	@RabbitListener(queues = "${queue}")
	@RabbitHandler
	public void process(String mes) {
		MyWebsocketMessage myWebsocketMessage=JSONObject.parseObject(mes, MyWebsocketMessage.class);
<<<<<<< HEAD

=======
		websocketService.sendMessage(myWebsocketMessage);
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
	}

}
