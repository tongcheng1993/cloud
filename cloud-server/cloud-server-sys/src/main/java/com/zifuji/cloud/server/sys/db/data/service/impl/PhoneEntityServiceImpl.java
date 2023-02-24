package com.zifuji.cloud.server.sys.db.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.data.entity.PhoneEntity;
import com.zifuji.cloud.server.sys.db.data.mapper.PhoneEntityMapper;
import com.zifuji.cloud.server.sys.db.data.service.PhoneEntityService;
import org.springframework.stereotype.Service;

@Service
public class PhoneEntityServiceImpl extends ServiceImpl<PhoneEntityMapper, PhoneEntity> implements PhoneEntityService {
}
