package com.zifuji.cloud.server.sys.module.template.service.impl;

import com.zifuji.cloud.server.sys.db.template.entity.TemplateEntity;
import com.zifuji.cloud.server.sys.db.template.service.TemplateEntityService;
import com.zifuji.cloud.server.sys.module.template.component.FreemarkerTemplateComponent;
import com.zifuji.cloud.server.sys.module.template.service.TemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class TemplateServiceImpl implements TemplateService {

    private FreemarkerTemplateComponent freemarkerTemplateComponent;

    private TemplateEntityService templateEntityService;

    @Override
    public TemplateEntity save(TemplateEntity templateEntity) {

        templateEntityService.save(templateEntity);
        return templateEntity;
    }

    @Override
    public String generateTemplateById(Long id, Map<String, Object> map) {
        String content = templateEntityService.getById(id).getContent();
        return freemarkerTemplateComponent.generateTemplate(content, map);
    }
}
