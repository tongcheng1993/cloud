package com.zifuji.cloud.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class CloudGatewayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayWebApplication.class, args);
		log.info("cloud-gateway-web项目开始");
	}
	
}
