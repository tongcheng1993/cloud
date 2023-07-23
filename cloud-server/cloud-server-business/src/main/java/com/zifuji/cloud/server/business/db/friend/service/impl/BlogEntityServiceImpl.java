package com.zifuji.cloud.server.business.db.friend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.friend.entity.BlogEntity;
import com.zifuji.cloud.server.business.db.friend.mapper.BlogEntityMapper;
import com.zifuji.cloud.server.business.db.friend.service.BlogEntityService;
import org.springframework.stereotype.Service;

@Service
public class BlogEntityServiceImpl extends ServiceImpl<BlogEntityMapper, BlogEntity>implements BlogEntityService {
}
