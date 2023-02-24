package com.zifuji.cloud.server.sys.db.email.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.email.entity.EmailTemplateEntity;
import com.zifuji.cloud.server.sys.db.email.mapper.EmailTemplateEntityMapper;
import com.zifuji.cloud.server.sys.db.email.service.EmailTemplateEntityService;

@Service
public class EmailTemplateEntityServiceImpl extends ServiceImpl<EmailTemplateEntityMapper, EmailTemplateEntity> implements EmailTemplateEntityService{

}
