package com.zifuji.cloud.server.sys.db.user.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.user.entity.WebRolePermissionEntity;
import com.zifuji.cloud.server.sys.db.user.mapper.WebRolePermissionEntityMapper;
import com.zifuji.cloud.server.sys.db.user.service.WebRolePermissionEntityService;

@Service
public class WebRolePermissionEntityServiceImpl extends ServiceImpl<WebRolePermissionEntityMapper, WebRolePermissionEntity>implements WebRolePermissionEntityService {

}
