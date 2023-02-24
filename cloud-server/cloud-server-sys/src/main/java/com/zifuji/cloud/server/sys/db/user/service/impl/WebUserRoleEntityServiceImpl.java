package com.zifuji.cloud.server.sys.db.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.user.entity.WebUserRoleEntity;
import com.zifuji.cloud.server.sys.db.user.mapper.WebUserRoleEntityMapper;
import com.zifuji.cloud.server.sys.db.user.service.WebUserRoleEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebUserRoleEntityServiceImpl extends ServiceImpl<WebUserRoleEntityMapper, WebUserRoleEntity> implements WebUserRoleEntityService {
}
