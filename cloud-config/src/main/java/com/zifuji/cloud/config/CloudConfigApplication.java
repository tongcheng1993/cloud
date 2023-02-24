package com.zifuji.cloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class CloudConfigApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudConfigApplication.class, args);
		log.info("CloudConfig项目开始了");
	}
}
