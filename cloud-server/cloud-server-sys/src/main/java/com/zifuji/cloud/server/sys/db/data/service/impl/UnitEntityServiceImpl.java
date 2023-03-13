package com.zifuji.cloud.server.sys.db.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.data.entity.UnitEntity;
import com.zifuji.cloud.server.sys.db.data.mapper.UnitEntityMapper;
import com.zifuji.cloud.server.sys.db.data.service.UnitEntityService;
import org.springframework.stereotype.Service;

@Service
public class UnitEntityServiceImpl extends ServiceImpl<UnitEntityMapper, UnitEntity> implements UnitEntityService {
}
