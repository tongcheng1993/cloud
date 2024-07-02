package com.zifuji.cloud.server.sys.db.manageUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.manageUser.entity.ManageRoleEntity;
import com.zifuji.cloud.server.sys.db.manageUser.mapper.ManageRoleEntityMapper;
import com.zifuji.cloud.server.sys.db.manageUser.service.ManageRoleEntityService;
import org.springframework.stereotype.Service;

@Service
public class ManageRoleEntityServiceImpl extends ServiceImpl<ManageRoleEntityMapper, ManageRoleEntity> implements ManageRoleEntityService {
}
