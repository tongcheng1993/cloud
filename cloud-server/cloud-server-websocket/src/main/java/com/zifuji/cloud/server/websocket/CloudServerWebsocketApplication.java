package com.zifuji.cloud.server.websocket;

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
public class CloudServerWebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudServerWebsocketApplication.class, args);
        log.info("websocket项目开始");
    }
}
