package com.zifuji.cloud.server.websocket.db.active.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.websocket.db.active.entity.WsActiveEntity;
import com.zifuji.cloud.server.websocket.db.active.mapper.WsActiveEntityMapper;
import com.zifuji.cloud.server.websocket.db.active.service.WsActiveEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WsActiveEntityServiceImpl extends ServiceImpl<WsActiveEntityMapper, WsActiveEntity> implements WsActiveEntityService {
}
