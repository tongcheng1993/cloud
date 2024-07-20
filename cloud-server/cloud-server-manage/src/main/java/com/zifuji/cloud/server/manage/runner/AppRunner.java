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

	private ManageUserEntityService manageUserEntityService;
	private ManageRoleEntityService manageRoleEntityService;
	private ManagePermissionEntityService managePermissionEntityService;
	private ManageMenuEntityService manageMenuEntityService;
	private ManageUserRoleEntityService manageUserRoleEntityService;
	private ManageRoleMenuEntityService manageRoleMenuEntityService;
	private ManageRolePermissionEntityService manageRolePermissionEntityService;

	private ApplicationContext applicationContext;

	public List<String> getAllPreAuthorizeExpressions() {
		List<String> preAuthorizeExpressions = new ArrayList<>();
		preAuthorizeExpressions.add("sys:manageUser:queryPageManageUser");
		preAuthorizeExpressions.add("sys:manageUser:getManageUserById");
		preAuthorizeExpressions.add("sys:manageUser:getManageUserByUserName");
		preAuthorizeExpressions.add("sys:manageUser:addManageUser");
		preAuthorizeExpressions.add("sys:manageUser:resetManageUser");
		preAuthorizeExpressions.add("sys:manageUser:queryListManageRole");
		preAuthorizeExpressions.add("sys:manageUser:bindUserAndRoleDelBefore");
		preAuthorizeExpressions.add("sys:manageUser:queryPageManageRole");
		preAuthorizeExpressions.add("sys:manageUser:getManageRoleById");
		preAuthorizeExpressions.add("sys:manageUser:getManageRoleByRoleCode");
		preAuthorizeExpressions.add("sys:manageUser:addManageRole");
		preAuthorizeExpressions.add("sys:manageUser:resetManageRole");
		preAuthorizeExpressions.add("sys:manageUser:queryListManagePermission");
		preAuthorizeExpressions.add("sys:manageUser:queryListManageMenu");
		preAuthorizeExpressions.add("sys:manageUser:bindRoleAndPermissionDelBefore");
		preAuthorizeExpressions.add("sys:manageUser:bindRoleAndMenuDelBefore");
		preAuthorizeExpressions.add("sys:manageUser:queryPageManagePermission");
		preAuthorizeExpressions.add("sys:manageUser:addManagePermission");
		preAuthorizeExpressions.add("sys:manageUser:resetManagePermission");
		return preAuthorizeExpressions;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("当系统启动时，会自动执行此方法，用来初始化系统数据");
		Set<String> set = args.getOptionNames();
		set.forEach(name -> {
			log.info("{}:{}", name, args.getOptionValues(name));
		});
		createRootUser();
	}

	private void createRootUser() {
		int manageMenuEntityCount = manageMenuEntityService.count();
		int managePermissionEntityCount = managePermissionEntityService.count();
		int manageRoleEntityCount = manageRoleEntityService.count();
		int manageUserEntityCount = manageUserEntityService.count();

		if (0 == manageMenuEntityCount) {
			// 管理端用户
			ManageMenuEntity manageMenuEntity = new ManageMenuEntity();
			manageMenuEntity.setShowFlag(true);
			manageMenuEntity.setParentId(0L);
			manageMenuEntity.setPath("dashboard");
			manageMenuEntity.setComponent("/dashboard/dashboard");
			manageMenuEntity.setLabel("首页");
			manageMenuEntity.setName("首页");
			manageMenuEntityService.save(manageMenuEntity);
			ManageMenuEntity manageMenuEntity1 = new ManageMenuEntity();
			manageMenuEntity1.setShowFlag(true);
			manageMenuEntity1.setParentId(0L);
			manageMenuEntity1.setPath("manageUserGroup");
			manageMenuEntity1.setComponent("");
			manageMenuEntity1.setLabel("管理端用户");
			manageMenuEntity1.setName("管理端用户");
			manageMenuEntityService.save(manageMenuEntity1);
			ManageMenuEntity manageMenuEntity2 = new ManageMenuEntity();
			manageMenuEntity2.setShowFlag(true);
			manageMenuEntity2.setParentId(manageMenuEntity1.getTableId());
			manageMenuEntity2.setPath("manageUserListView");
			manageMenuEntity2.setComponent("/manageUser/manageUserList");
			manageMenuEntity2.setLabel("用户列表");
			manageMenuEntity2.setName("用户列表");
			manageMenuEntityService.save(manageMenuEntity2);
			ManageMenuEntity manageMenuEntity3 = new ManageMenuEntity();
			manageMenuEntity3.setShowFlag(true);
			manageMenuEntity3.setParentId(manageMenuEntity1.getTableId());
			manageMenuEntity3.setPath("manageRoleListView");
			manageMenuEntity3.setComponent("/manageUser/manageRoleList");
			manageMenuEntity3.setLabel("角色列表");
			manageMenuEntity3.setName("角色列表");
			manageMenuEntityService.save(manageMenuEntity3);
			ManageMenuEntity manageMenuEntity4 = new ManageMenuEntity();
			manageMenuEntity4.setShowFlag(true);
			manageMenuEntity4.setParentId(manageMenuEntity1.getTableId());
			manageMenuEntity4.setPath("manageMenuListView");
			manageMenuEntity4.setComponent("/manageUser/manageMenuList");
			manageMenuEntity4.setLabel("菜单列表");
			manageMenuEntity4.setName("菜单列表");
			manageMenuEntityService.save(manageMenuEntity4);
			ManageMenuEntity manageMenuEntity5 = new ManageMenuEntity();
			manageMenuEntity5.setShowFlag(true);
			manageMenuEntity5.setParentId(manageMenuEntity1.getTableId());
			manageMenuEntity5.setPath("managePermissionListView");
			manageMenuEntity5.setComponent("/manageUser/managePermissionList");
			manageMenuEntity5.setLabel("接口列表");
			manageMenuEntity5.setName("接口列表");
			manageMenuEntityService.save(manageMenuEntity5);
			if (0 == manageRoleEntityCount) {
				ManageRoleEntity manageRoleEntity = new ManageRoleEntity();
				manageRoleEntity.setRoleCode("root");
				manageRoleEntity.setRoleName("root");
				manageRoleEntity.setRoleDescription("root");
				manageRoleEntityService.save(manageRoleEntity);
				ManageRoleMenuEntity manageRoleMenuEntity = new ManageRoleMenuEntity();
				manageRoleMenuEntity.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity.setMenuId(manageMenuEntity.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity);
				ManageRoleMenuEntity manageRoleMenuEntity1 = new ManageRoleMenuEntity();
				manageRoleMenuEntity1.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity1.setMenuId(manageMenuEntity1.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity1);
				ManageRoleMenuEntity manageRoleMenuEntity2 = new ManageRoleMenuEntity();
				manageRoleMenuEntity2.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity2.setMenuId(manageMenuEntity2.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity2);
				ManageRoleMenuEntity manageRoleMenuEntity3 = new ManageRoleMenuEntity();
				manageRoleMenuEntity3.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity3.setMenuId(manageMenuEntity3.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity3);
				ManageRoleMenuEntity manageRoleMenuEntity4 = new ManageRoleMenuEntity();
				manageRoleMenuEntity4.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity4.setMenuId(manageMenuEntity4.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity4);
				ManageRoleMenuEntity manageRoleMenuEntity5 = new ManageRoleMenuEntity();
				manageRoleMenuEntity5.setRoleId(manageRoleEntity.getTableId());
				manageRoleMenuEntity5.setMenuId(manageMenuEntity5.getTableId());
				manageRoleMenuEntityService.save(manageRoleMenuEntity5);

				if (0 == managePermissionEntityCount) {
					List<String> PreAuthorizeExpressionList = getAllPreAuthorizeExpressions();
					for (String str : PreAuthorizeExpressionList) {
						String[] strArr = str.split(":");
						QueryWrapper<ManagePermissionEntity> managePermissionEntityQueryWrapper = new QueryWrapper<ManagePermissionEntity>();
						managePermissionEntityQueryWrapper.lambda().eq(ManagePermissionEntity::getCodeSys, strArr[0])
								.eq(ManagePermissionEntity::getCodeModule, strArr[1])
								.eq(ManagePermissionEntity::getCodeMethod, strArr[2]);
						ManagePermissionEntity managePermissionEntity = managePermissionEntityService
								.getOne(managePermissionEntityQueryWrapper);
						if (ObjectUtil.isNull(managePermissionEntity)) {
							managePermissionEntity = new ManagePermissionEntity();
							managePermissionEntity.setPerName(str);
							managePermissionEntity.setCodeSys(strArr[0]);
							managePermissionEntity.setCodeModule(strArr[1]);
							managePermissionEntity.setCodeMethod(strArr[2]);
							managePermissionEntityService.save(managePermissionEntity);
							
							ManageRolePermissionEntity manageRolePermissionEntity = new ManageRolePermissionEntity();
							manageRolePermissionEntity.setRoleId(manageRoleEntity.getTableId());
							manageRolePermissionEntity.setPermissionId(managePermissionEntity.getTableId());
							manageRolePermissionEntityService.save(manageRolePermissionEntity);
						}
					}

				}

				if (0 == manageUserEntityCount) {
					ManageUserEntity manageUserEntity = new ManageUserEntity();
					manageUserEntity.setUserName("root");
					// 密码加密
					BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
					manageUserEntity.setPassWord(bCryptPasswordEncoder.encode("root123456"));
					manageUserEntity.setShortName("root");
					manageUserEntityService.save(manageUserEntity);

					ManageUserRoleEntity manageUserRoleEntity = new ManageUserRoleEntity();
					manageUserRoleEntity.setUserId(manageUserEntity.getTableId());
					manageUserRoleEntity.setRoleId(manageRoleEntity.getTableId());
					manageUserRoleEntityService.save(manageUserRoleEntity);
				}
			}

		}

	}
}
