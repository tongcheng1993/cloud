package com.zifuji.cloud.gateway.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
public class CloudGatewayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudGatewayWebApplication.class, args);
		log.info("gateway2项目开始");
	}
	
}
