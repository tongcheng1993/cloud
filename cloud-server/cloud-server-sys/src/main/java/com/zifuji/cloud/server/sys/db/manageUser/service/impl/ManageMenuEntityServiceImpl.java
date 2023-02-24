package com.zifuji.cloud.server.sys.db.manageUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.manageUser.entity.ManageMenuEntity;
import com.zifuji.cloud.server.sys.db.manageUser.mapper.ManageMenuEntityMapper;
import com.zifuji.cloud.server.sys.db.manageUser.service.ManageMenuEntityService;
import org.springframework.stereotype.Service;

@Service
public class ManageMenuEntityServiceImpl extends ServiceImpl<ManageMenuEntityMapper, ManageMenuEntity> implements ManageMenuEntityService {
}
