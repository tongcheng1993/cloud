package com.zifuji.cloud.server.base.config;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zifuji.cloud.server.base.object.MetaHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyBatisPlusConfig {

	public MyBatisPlusConfig() {
		System.out.println("MyBatisPlusConfig");
	}

	@Bean
	public MetaObjectHandler getMetaObjectHandler() {
		log.info("new MetaHandler()");
		return new MetaHandler();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDbType(DbType.MYSQL);
		return page;
	}

	@Bean
	public Snowflake getSnowflake(){
		return new Snowflake();
	}

}
