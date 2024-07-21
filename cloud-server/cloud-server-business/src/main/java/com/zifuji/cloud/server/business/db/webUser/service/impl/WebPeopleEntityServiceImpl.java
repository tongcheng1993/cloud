package com.zifuji.cloud.server.business.db.webUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.webUser.entity.WebPeopleEntity;
import com.zifuji.cloud.server.business.db.webUser.mapper.WebPeopleEntityMapper;
import com.zifuji.cloud.server.business.db.webUser.service.WebPeopleEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebPeopleEntityServiceImpl extends ServiceImpl<WebPeopleEntityMapper, WebPeopleEntity> implements WebPeopleEntityService {
}
