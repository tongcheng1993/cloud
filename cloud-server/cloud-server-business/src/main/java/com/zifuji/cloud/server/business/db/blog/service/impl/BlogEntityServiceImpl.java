package com.zifuji.cloud.server.business.db.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.blog.entity.BlogEntity;
import com.zifuji.cloud.server.business.db.blog.mapper.BlogEntityMapper;
import com.zifuji.cloud.server.business.db.blog.service.BlogEntityService;
import org.springframework.stereotype.Service;

@Service
public class BlogEntityServiceImpl extends ServiceImpl<BlogEntityMapper, BlogEntity> implements BlogEntityService {
}
