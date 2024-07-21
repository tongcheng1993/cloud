package com.zifuji.cloud.server.business.db.webUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.webUser.entity.WebUserRoleEntity;
import com.zifuji.cloud.server.business.db.webUser.mapper.WebUserRoleEntityMapper;
import com.zifuji.cloud.server.business.db.webUser.service.WebUserRoleEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebUserRoleEntityServiceImpl extends ServiceImpl<WebUserRoleEntityMapper, WebUserRoleEntity> implements WebUserRoleEntityService {
}
