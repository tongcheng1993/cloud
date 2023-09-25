package com.zifuji.cloud.gateway.web.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "web.ignore")
public class WebIgnoreProperties {

	
	private List<String> uri;
}
