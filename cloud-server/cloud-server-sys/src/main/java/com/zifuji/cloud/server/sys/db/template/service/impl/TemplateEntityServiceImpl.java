package com.zifuji.cloud.server.sys.db.template.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.template.entity.TemplateEntity;
import com.zifuji.cloud.server.sys.db.template.mapper.TemplateEntityMapper;
import com.zifuji.cloud.server.sys.db.template.service.TemplateEntityService;
import org.springframework.stereotype.Service;

@Service
public class TemplateEntityServiceImpl extends ServiceImpl<TemplateEntityMapper, TemplateEntity> implements TemplateEntityService {
}
