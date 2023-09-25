package com.zifuji.cloud.gateway.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayManageApplication.class, args);
		log.info("cloud-gateway-manage项目开始");
	}
}
