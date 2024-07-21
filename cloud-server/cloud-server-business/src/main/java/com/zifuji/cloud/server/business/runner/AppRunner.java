package com.zifuji.cloud.server.business.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.zifuji.cloud.server.business.db.webUser.entity.WebRoleEntity;
import com.zifuji.cloud.server.business.db.webUser.service.WebRoleEntityService;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {

	
	private WebRoleEntityService webRoleEntityService;
	
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("当系统启动时，会自动执行此方法，用来初始化系统数据--start");
        Set<String> set = args.getOptionNames();
        set.forEach(name -> {
            log.info("{}:{}", name, args.getOptionValues(name));
        });
        createRoleRegister();
        log.debug("当系统启动时，会自动执行此方法，用来初始化系统数据--end");
    }
    
    
    private void createRoleRegister() {
    	int roleCount = webRoleEntityService.count();
    	if (0 == roleCount) {
    		WebRoleEntity webRoleEntity = new WebRoleEntity();
    		webRoleEntity.setName("register");
    		webRoleEntity.setCode("register");
    		webRoleEntity.setDescription("register");
    		webRoleEntityService.save(webRoleEntity);
    	}
    }
}
