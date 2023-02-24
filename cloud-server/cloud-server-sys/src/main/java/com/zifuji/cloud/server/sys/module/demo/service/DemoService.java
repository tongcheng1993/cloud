package com.zifuji.cloud.server.sys.module.demo.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;

public interface DemoService {

	
	IPage<String> queryPageDemo();
	
	List<String> queryListDemo();

	String getDemoInfoById();	
	
	String saveDemoInfo();
	
	String updateDemoInfo();
	
	Boolean delDemoInfo();
	
	
}
