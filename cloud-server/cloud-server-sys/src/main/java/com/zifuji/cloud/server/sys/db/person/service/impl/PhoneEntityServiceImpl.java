package com.zifuji.cloud.server.sys.db.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.person.entity.PhoneEntity;
import com.zifuji.cloud.server.sys.db.person.mapper.PhoneEntityMapper;
import com.zifuji.cloud.server.sys.db.person.service.PhoneEntityService;
import org.springframework.stereotype.Service;

@Service
public class PhoneEntityServiceImpl extends ServiceImpl<PhoneEntityMapper, PhoneEntity> implements PhoneEntityService {
}
