package com.zifuji.cloud.server.sys.db.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.user.entity.WebUserEntity;
import com.zifuji.cloud.server.sys.db.user.mapper.WebUserEntityMapper;
import com.zifuji.cloud.server.sys.db.user.service.WebUserEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebUserEntityServiceImpl extends ServiceImpl<WebUserEntityMapper, WebUserEntity>implements WebUserEntityService {
}
