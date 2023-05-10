package com.zifuji.cloud.server.websocket.db.message.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.websocket.db.message.entity.WsMessageEntity;
import com.zifuji.cloud.server.websocket.db.message.mapper.WsMessageEntityMapper;
import com.zifuji.cloud.server.websocket.db.message.service.WsMessageEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WsMessageEntityServiceImpl extends ServiceImpl<WsMessageEntityMapper, WsMessageEntity> implements WsMessageEntityService {
}
