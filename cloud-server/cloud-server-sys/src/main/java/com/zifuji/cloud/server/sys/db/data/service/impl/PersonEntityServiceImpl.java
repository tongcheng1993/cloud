package com.zifuji.cloud.server.sys.db.data.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.data.entity.PersonEntity;
import com.zifuji.cloud.server.sys.db.data.mapper.PersonEntityMapper;
import com.zifuji.cloud.server.sys.db.data.service.PersonEntityService;

@Service
public class PersonEntityServiceImpl extends ServiceImpl<PersonEntityMapper, PersonEntity> implements PersonEntityService{

}
