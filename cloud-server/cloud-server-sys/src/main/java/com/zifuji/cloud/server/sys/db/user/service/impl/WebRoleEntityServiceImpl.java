package com.zifuji.cloud.server.sys.db.user.service.impl;


import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.user.entity.WebRoleEntity;
import com.zifuji.cloud.server.sys.db.user.mapper.WebRoleEntityMapper;
import com.zifuji.cloud.server.sys.db.user.service.WebRoleEntityService;

@Service
public class WebRoleEntityServiceImpl extends ServiceImpl<WebRoleEntityMapper, WebRoleEntity> implements WebRoleEntityService {

}
