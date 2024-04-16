package com.zifuji.cloud.server.sys.db.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.person.entity.UnitEntity;
import com.zifuji.cloud.server.sys.db.person.mapper.UnitEntityMapper;
import com.zifuji.cloud.server.sys.db.person.service.UnitEntityService;
import org.springframework.stereotype.Service;

@Service
public class UnitEntityServiceImpl extends ServiceImpl<UnitEntityMapper, UnitEntity> implements UnitEntityService {
}
