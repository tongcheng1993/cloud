package com.zifuji.cloud.server.sys.db.manageUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.manageUser.entity.ManageUserEntity;
import com.zifuji.cloud.server.sys.db.manageUser.mapper.ManageUserEntityMapper;
import com.zifuji.cloud.server.sys.db.manageUser.service.ManageUserEntityService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class ManageUserEntityServiceImpl extends ServiceImpl<ManageUserEntityMapper, ManageUserEntity> implements ManageUserEntityService {

    @Override
    public boolean save(ManageUserEntity entity) {


        return super.save(entity);
    }

    @Override
    public boolean removeById(Serializable id) {


        return super.removeById(id);
    }

    @Override
    public boolean updateById(ManageUserEntity entity) {


        return super.updateById(entity);
    }

    @Override
    public ManageUserEntity getById(Serializable id) {


        return super.getById(id);
    }
}