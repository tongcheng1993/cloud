package com.zifuji.cloud.server.websocket.db.wsMessage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.websocket.db.wsMessage.entity.WsMessageEntity;
import com.zifuji.cloud.server.websocket.db.wsMessage.mapper.WsMessageEntityMapper;
import com.zifuji.cloud.server.websocket.db.wsMessage.service.WsMessageEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WsMessageEntityServiceImpl extends ServiceImpl<WsMessageEntityMapper, WsMessageEntity> implements WsMessageEntityService {
}
