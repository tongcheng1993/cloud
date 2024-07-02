package com.zifuji.cloud.server.base.module.es.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "zfj.es")
public class ZfjEsProperties {

    private String url = "http://localhost:9200";


}
