package com.zifuji.cloud.server.business.db.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.friend.entity.FriendNotePersonEntity;
import com.zifuji.cloud.server.business.db.friend.mapper.FriendNoteEntityMapper;
import com.zifuji.cloud.server.business.db.friend.service.FriendNoteEntityService;
import org.springframework.stereotype.Service;

@Service
public class FriendNoteEntityServiceImpl extends ServiceImpl<FriendNoteEntityMapper, FriendNotePersonEntity> implements FriendNoteEntityService {
}
