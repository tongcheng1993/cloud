package com.zifuji.cloud.server.websocket.db.wsTypePath.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.websocket.db.wsTypePath.entity.WsTypePathEntity;
import com.zifuji.cloud.server.websocket.db.wsTypePath.mapper.WsTypePathEntityMapper;
import com.zifuji.cloud.server.websocket.db.wsTypePath.service.WsTypePathEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WsTypePathEntityServiceImpl extends ServiceImpl<WsTypePathEntityMapper, WsTypePathEntity> implements WsTypePathEntityService {
}
