package com.zifuji.cloud.server.business;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@EnableFeignClients
@EnableCaching
@SpringBootApplication
@EnableDiscoveryClient
public class CloudServerBusinessApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerBusinessApplication.class, args);
        log.info("business 项目开始");
    }
}
