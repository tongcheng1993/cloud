package com.zifuji.cloud.server.office;

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
public class CloudServerOfficeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerOfficeApplication.class, args);
        log.info("office软件服务项目开始");
    }
}
