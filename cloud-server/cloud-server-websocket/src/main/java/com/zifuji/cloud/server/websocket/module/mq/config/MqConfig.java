package com.zifuji.cloud.server.websocket.module.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MqConfig {

	@Value("${queue}")
	private String queue;

	 
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("wsExchange");
    }
    
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }
    
    @Bean
    public Binding bindingExchangeA() {
        return BindingBuilder.bind(queue()).to(fanoutExchange());
    }
    
    
	
}
