package com.zifuji.cloud.server.websocket.module.websocket.service;



import com.zifuji.cloud.server.websocket.module.websocket.controller.mo.SendWsMessageMo;

public interface WebsocketService {

    Boolean sendWsAllMessage(SendWsMessageMo sendWsMessageMo);

    Boolean sendWsOneMessage(SendWsMessageMo sendWsMessageMo);

    Boolean sendWsMessage(SendWsMessageMo sendWsMessageMo);

    Integer info();
}
