package com.zifuji.cloud.server.websocket.db.message.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zifuji.cloud.server.websocket.db.message.entity.WsMessageEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WsMessageEntityMapper extends BaseMapper<WsMessageEntity> {
}
