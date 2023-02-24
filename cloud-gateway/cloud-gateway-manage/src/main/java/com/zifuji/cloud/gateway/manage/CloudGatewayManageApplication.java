package com.zifuji.cloud.gateway.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayManageApplication.class, args);
		log.info("gateway1项目开始");
	}
}
