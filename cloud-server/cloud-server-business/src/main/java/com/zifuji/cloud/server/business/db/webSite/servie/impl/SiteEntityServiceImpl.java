package com.zifuji.cloud.server.business.db.website.servie.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.website.entity.SiteEntity;
import com.zifuji.cloud.server.business.db.website.mapper.SiteEntityMapper;
import com.zifuji.cloud.server.business.db.website.servie.SiteEntityService;

@Service
public class SiteEntityServiceImpl extends ServiceImpl<SiteEntityMapper, SiteEntity> implements SiteEntityService {

}
