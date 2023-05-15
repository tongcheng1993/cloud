package com.zifuji.cloud.server.business.db.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.friend.entity.FriendRelationEntity;
import com.zifuji.cloud.server.business.db.friend.mapper.FriendRelationEntityMapper;
import com.zifuji.cloud.server.business.db.friend.service.FriendRelationEntityService;
import org.springframework.stereotype.Service;

@Service
public class FriendRelationEntityServiceImpl extends ServiceImpl<FriendRelationEntityMapper, FriendRelationEntity> implements FriendRelationEntityService {
}
