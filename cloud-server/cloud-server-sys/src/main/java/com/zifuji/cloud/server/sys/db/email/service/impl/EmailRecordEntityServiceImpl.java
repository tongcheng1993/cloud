package com.zifuji.cloud.server.sys.db.email.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.email.entity.EmailRecordEntity;
import com.zifuji.cloud.server.sys.db.email.mapper.EmailRecordEntityMapper;
import com.zifuji.cloud.server.sys.db.email.service.EmailRecordEntityService;

@Service
public class EmailRecordEntityServiceImpl extends ServiceImpl<EmailRecordEntityMapper, EmailRecordEntity> implements EmailRecordEntityService{

}
