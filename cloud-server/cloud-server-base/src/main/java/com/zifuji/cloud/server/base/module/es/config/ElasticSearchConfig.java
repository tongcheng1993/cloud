package com.zifuji.cloud.server.base.module.es.config;

import com.zifuji.cloud.server.base.module.es.properties.ZfjEsProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ElasticSearchConfig {


    @Bean
    public RestHighLevelClient getRestHighLevelClient(ZfjEsProperties zfjEsProperties) {
        HttpHost host = HttpHost.create(zfjEsProperties.getUrl());
        RestClientBuilder builder = RestClient.builder(host);
        return new RestHighLevelClient(builder);
    }


}
