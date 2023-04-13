package com.zifuji.cloud.server.base.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zifuji.cloud.server.base.object.MetaHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


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
