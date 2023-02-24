package com.zifuji.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class CloudEurekaApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaApplication.class, args);
		log.info("CloudEureka项目开始了");
	}
}
