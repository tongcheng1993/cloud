package com.zifuji.cloud.server.sys.db.person.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.person.entity.PersonEntity;
import com.zifuji.cloud.server.sys.db.person.mapper.PersonEntityMapper;
import com.zifuji.cloud.server.sys.db.person.service.PersonEntityService;

@Service
public class PersonEntityServiceImpl extends ServiceImpl<PersonEntityMapper, PersonEntity> implements PersonEntityService{

}
