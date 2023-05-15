package com.zifuji.cloud.server.business;

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
public class CloudServerBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerBusinessApplication.class, args);
        log.info("business 项目开始");
    }
}
