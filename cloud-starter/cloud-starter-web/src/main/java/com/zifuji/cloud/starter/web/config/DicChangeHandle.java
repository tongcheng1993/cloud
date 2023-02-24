package com.zifuji.cloud.starter.web.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DicChangeHandle {
	public DicChangeHandle() {
		System.out.println("DicChangeHandle");
	}
	
	
	

}
