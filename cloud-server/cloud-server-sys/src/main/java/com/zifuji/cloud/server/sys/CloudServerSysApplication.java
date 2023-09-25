package com.zifuji.cloud.server.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableTransactionManagement
@EnableCaching
public class CloudServerSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerSysApplication.class, args);
        log.info("网站框架sys项目开始");
    }
}
