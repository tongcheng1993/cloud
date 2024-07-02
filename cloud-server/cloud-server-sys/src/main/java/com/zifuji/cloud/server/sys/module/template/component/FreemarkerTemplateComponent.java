package com.zifuji.cloud.server.sys.module.template.component;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.io.StringWriter;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class FreemarkerTemplateComponent {

    public String generateTemplate(String templateContent, Map<String, Object> map) {
        String result = "";
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            Template template = new Template("id", templateContent, configuration);
            StringWriter stringWriter = new StringWriter();
            template.process(map, stringWriter);
            result = stringWriter.toString();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }


}
