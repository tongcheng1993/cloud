package com.zifuji.cloud.server.sys.db.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.friend.entity.FriendNoteEntity;
import com.zifuji.cloud.server.sys.db.friend.mapper.FriendNoteEntityMapper;
import com.zifuji.cloud.server.sys.db.friend.service.FriendNoteEntityService;
import org.springframework.stereotype.Service;

@Service
public class FriendNoteEntityServiceImpl extends ServiceImpl<FriendNoteEntityMapper, FriendNoteEntity> implements FriendNoteEntityService {
}
