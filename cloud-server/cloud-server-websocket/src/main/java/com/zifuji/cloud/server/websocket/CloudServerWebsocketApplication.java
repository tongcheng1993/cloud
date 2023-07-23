package com.zifuji.cloud.server.websocket;

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
public class CloudServerWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerWebsocketApplication.class, args);
        log.info("websocket项目开始");
    }
}
