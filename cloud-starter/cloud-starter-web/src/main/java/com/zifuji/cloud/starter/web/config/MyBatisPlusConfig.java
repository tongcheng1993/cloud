package com.zifuji.cloud.starter.web.config;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zifuji.cloud.starter.web.object.MetaHandler;


@Configuration
public class MyBatisPlusConfig {

	public MyBatisPlusConfig() {
		System.out.println("MyBatisPlusConfig");
	}

	@Bean
	public MetaObjectHandler getMetaObjectHandler() {
		return new MetaHandler();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor page = new PaginationInterceptor();
		page.setDbType(DbType.MYSQL);
		return page;
	}
}
