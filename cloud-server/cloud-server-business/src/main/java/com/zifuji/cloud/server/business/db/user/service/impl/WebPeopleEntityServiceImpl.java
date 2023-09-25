package com.zifuji.cloud.server.business.db.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.user.entity.WebPeopleEntity;
import com.zifuji.cloud.server.business.db.user.mapper.WebPeopleEntityMapper;
import com.zifuji.cloud.server.business.db.user.service.WebPeopleEntityService;
import org.springframework.stereotype.Service;

@Service
public class WebPeopleEntityServiceImpl extends ServiceImpl<WebPeopleEntityMapper, WebPeopleEntity> implements WebPeopleEntityService {
}