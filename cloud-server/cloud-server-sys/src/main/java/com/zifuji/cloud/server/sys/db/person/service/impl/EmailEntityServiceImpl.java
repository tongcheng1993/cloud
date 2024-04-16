package com.zifuji.cloud.server.sys.db.person.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.person.entity.EmailEntity;
import com.zifuji.cloud.server.sys.db.person.mapper.EmailEntityMapper;
import com.zifuji.cloud.server.sys.db.person.service.EmailEntityService;
import org.springframework.stereotype.Service;

@Service
public class EmailEntityServiceImpl extends ServiceImpl<EmailEntityMapper, EmailEntity> implements EmailEntityService {
}
