package com.zifuji.cloud.server.business.db.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.friend.entity.FriendBlogEntity;
import com.zifuji.cloud.server.business.db.friend.mapper.FriendBlogEntityMapper;
import com.zifuji.cloud.server.business.db.friend.service.FriendBlogEntityService;
import org.springframework.stereotype.Service;

@Service
public class FriendBlogEntityServiceImpl extends ServiceImpl<FriendBlogEntityMapper, FriendBlogEntity>implements FriendBlogEntityService {
}
