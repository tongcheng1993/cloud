package com.zifuji.cloud.server.manage.db.manageUser.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageMenuEntity;
import com.zifuji.cloud.server.manage.db.manageUser.mapper.ManageMenuEntityMapper;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageMenuEntityService;

@Service
public class ManageMenuEntityServiceImpl extends ServiceImpl<ManageMenuEntityMapper, ManageMenuEntity> implements ManageMenuEntityService {
}