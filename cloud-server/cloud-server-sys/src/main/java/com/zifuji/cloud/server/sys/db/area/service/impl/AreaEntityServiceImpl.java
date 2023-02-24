package com.zifuji.cloud.server.sys.db.area.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.area.entity.AreaEntity;
import com.zifuji.cloud.server.sys.db.area.mapper.AreaEntityMapper;
import com.zifuji.cloud.server.sys.db.area.service.AreaEntityService;

@Service
public class AreaEntityServiceImpl extends ServiceImpl<AreaEntityMapper, AreaEntity> implements AreaEntityService{

}
