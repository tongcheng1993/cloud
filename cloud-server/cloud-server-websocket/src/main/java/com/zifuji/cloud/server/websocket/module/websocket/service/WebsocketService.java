package com.zifuji.cloud.server.websocket.module.websocket.service;



import com.zifuji.cloud.server.websocket.module.websocket.mo.SendWsMessageMo;

public interface WebsocketService {

    Boolean sendWsAllMessage(SendWsMessageMo sendWsMessageMo);

    Boolean sendWsMessage(SendWsMessageMo sendWsMessageMo);

    Integer info();
}
