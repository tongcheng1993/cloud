package com.zifuji.cloud.server.business.db.webUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.webUser.entity.WebUserEntity;
import com.zifuji.cloud.server.business.db.webUser.mapper.WebUserEntityMapper;
import com.zifuji.cloud.server.business.db.webUser.service.WebUserEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebUserEntityServiceImpl extends ServiceImpl<WebUserEntityMapper, WebUserEntity>implements WebUserEntityService {
}
