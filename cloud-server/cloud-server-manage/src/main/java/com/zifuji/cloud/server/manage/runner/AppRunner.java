package com.zifuji.cloud.server.manage.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageMenuEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManagePermissionEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageRoleEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageRoleMenuEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageRolePermissionEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageUserEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageUserRoleEntity;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageMenuEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManagePermissionEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageRoleEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageRoleMenuEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageRolePermissionEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageUserEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageUserRoleEntityService;

import cn.hutool.core.util.ObjectUtil;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {

	public void openArgs(ApplicationArguments args) {
		log.info("当系统启动时，会自动执行此方法，用来初始化系统数据");
		Set<String> set = args.getOptionNames();
		set.forEach(name -> {
			log.info("{}:{}", name, args.getOptionValues(name));
		});
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		openArgs(args);
		createRootUser();
	}

	private void createRootUser() {

	}
}
