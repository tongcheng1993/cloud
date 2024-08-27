package com.zifuji.cloud.server.business.module.webUser.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.zifuji.cloud.server.business.module.webUser.controller.mo.AddWebRoleMo;
import com.zifuji.cloud.server.business.module.webUser.service.WebUserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class WebUserRunner implements ApplicationRunner {
	
	private WebUserService webUserService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		initRole();

	}

	private void initRole() {
		try {
			AddWebRoleMo addWebRoleMo = new AddWebRoleMo();
			addWebRoleMo.setCode("register");
			addWebRoleMo.setName("register");
			addWebRoleMo.setDescription("register");
			webUserService.addWebRole(addWebRoleMo);
		} catch (Exception e) {

		}
	}

}
