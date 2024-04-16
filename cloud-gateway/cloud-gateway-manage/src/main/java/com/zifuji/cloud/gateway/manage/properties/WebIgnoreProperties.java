package com.zifuji.cloud.gateway.manage.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "my")
public class WebIgnoreProperties {

	
	private List<String> webUrl;
}
