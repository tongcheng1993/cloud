package com.zifuji.cloud.server.sys.db.manageUser.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.base.exception.Exception20000;
import com.zifuji.cloud.server.sys.db.manageUser.entity.ManagePermissionEntity;
import com.zifuji.cloud.server.sys.db.manageUser.mapper.ManagePermissionEntityMapper;
import com.zifuji.cloud.server.sys.db.manageUser.service.ManagePermissionEntityService;
import org.springframework.stereotype.Service;

@Service
public class ManagePermissionEntityServiceImpl extends ServiceImpl<ManagePermissionEntityMapper, ManagePermissionEntity> implements ManagePermissionEntityService {


    @Override
    public boolean save(ManagePermissionEntity entity) {
        QueryWrapper<ManagePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(ManagePermissionEntity::getCodeSys, entity.getCodeSys())
                .eq(ManagePermissionEntity::getCodeModule, entity.getCodeModule())
                .eq(ManagePermissionEntity::getCode, entity.getCode());
        ManagePermissionEntity temp = getOne(queryWrapper);
        if(ObjectUtil.isNotNull(temp)){
            throw new Exception20000("");
        }



        return super.save(entity);
    }
}
