package com.zifuji.cloud.server.websocket.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.method1(args);
	}

	/**
	 * 循环参数
	 * 
	 * @param args
	 */
	public void method1(ApplicationArguments args) {

	}
}
