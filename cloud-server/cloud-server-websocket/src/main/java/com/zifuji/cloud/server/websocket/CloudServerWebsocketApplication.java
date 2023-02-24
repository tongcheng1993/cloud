package com.zifuji.cloud.server.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class CloudServerWebsocketApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudServerWebsocketApplication.class, args);
		log.info("websocket项目开始");
	}
}
