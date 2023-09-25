package com.zifuji.cloud.server.business.db.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.user.entity.WebUserRoleEntity;
import com.zifuji.cloud.server.business.db.user.mapper.WebUserRoleEntityMapper;
import com.zifuji.cloud.server.business.db.user.service.WebUserRoleEntityService;
import org.springframework.stereotype.Service;

@Service
public class WebUserRoleEntityServiceImpl extends ServiceImpl<WebUserRoleEntityMapper, WebUserRoleEntity> implements WebUserRoleEntityService {
}