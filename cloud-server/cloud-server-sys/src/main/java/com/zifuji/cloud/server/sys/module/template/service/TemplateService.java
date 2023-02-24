package com.zifuji.cloud.server.sys.module.template.service;

import com.zifuji.cloud.server.sys.db.template.entity.TemplateEntity;

import java.util.Map;

public interface TemplateService {

    TemplateEntity save(TemplateEntity templateEntity);

    String generateTemplateById(Long id, Map<String, Object> map);
}
