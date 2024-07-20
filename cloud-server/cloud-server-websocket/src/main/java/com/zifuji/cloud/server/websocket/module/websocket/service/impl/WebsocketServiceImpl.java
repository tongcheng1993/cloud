package com.zifuji.cloud.server.websocket.module.websocket.service.impl;

import cn.hutool.core.bean.BeanUtil;

import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import com.zifuji.cloud.server.websocket.db.wsMessage.entity.WsMessageEntity;
import com.zifuji.cloud.server.websocket.db.wsMessage.service.WsMessageEntityService;
import com.zifuji.cloud.server.websocket.module.websocket.controller.mo.SendWsMessageMo;
import com.zifuji.cloud.server.websocket.module.websocket.service.WebsocketService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WebsocketServiceImpl implements WebsocketService {


    private SimpMessagingTemplate simpMessagingTemplate;

    private WsMessageEntityService wsMessageEntityService;


    @Override
    public Boolean sendWsAllMessage(SendWsMessageMo sendWsMessageMo) {
        sendWsMessageMo.setBusinessType(BaseConstant.WS_TYPE_TOPIC);
        sendWsMessage(sendWsMessageMo);
        return true;
    }

    @Override
    public Boolean sendWsOneMessage(SendWsMessageMo sendWsMessageMo) {
        sendWsMessageMo.setBusinessType(BaseConstant.WS_TYPE_PEOPLE);
        sendWsMessageMo.setTypePath(BaseConstant.WS_TYPE_PEOPLE_PATH);
        sendWsMessage(sendWsMessageMo);
        return null;
    }

    @Override
    public Boolean sendWsMessage(SendWsMessageMo sendWsMessageMo) {
    	UserInfo user = SecurityUtil.getUserDetails();
    	sendWsMessageMo.setFromUserId(user.getTableId());
    	sendWsMessageMo.setFromUserName(user.getShortName());
    	 WsMessageEntity wsMessageEntity = new WsMessageEntity();
         BeanUtil.copyProperties(sendWsMessageMo,wsMessageEntity);
         wsMessageEntityService.save(wsMessageEntity);
        if(StringUtils.equals(BaseConstant.WS_TYPE_TOPIC,sendWsMessageMo.getBusinessType())){
            simpMessagingTemplate.convertAndSend(wsMessageEntity.getTypePath(), wsMessageEntity);
        }else if(StringUtils.equals(BaseConstant.WS_TYPE_PEOPLE,sendWsMessageMo.getBusinessType())){
            simpMessagingTemplate.convertAndSendToUser(""+wsMessageEntity.getToUserId(), wsMessageEntity.getTypePath(), wsMessageEntity);
        }else{
            throw new Exception20000("没有找到对应的ws消息类型");
        }
        return true;
    }

    @Override
    public Integer info() {
        return 0;
    }

}
