package com.zifuji.cloud.server.ai;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableEurekaClient
@EnableFeignClients
@EnableCaching
@SpringBootApplication
public class CloudServerAiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServerAiApplication.class, args);
        log.info("人工智能ai项目开始");
    }
}
