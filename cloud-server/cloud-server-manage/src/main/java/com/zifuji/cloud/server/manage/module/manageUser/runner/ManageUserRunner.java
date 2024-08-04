package com.zifuji.cloud.server.manage.module.manageUser.runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageMenuEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManagePermissionEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageRoleEntity;
import com.zifuji.cloud.server.manage.db.manageUser.entity.ManageUserEntity;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageMenuEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManagePermissionEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageRoleEntityService;
import com.zifuji.cloud.server.manage.db.manageUser.service.ManageUserEntityService;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.AddManageMenuMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.AddManagePermissionMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.AddManageRoleMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.AddManageUserMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.BindRoleAndMenuDelBeforeMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.BindRoleAndPermissionDelBeforeMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.BindUserAndRoleDelBeforeMo;
import com.zifuji.cloud.server.manage.module.manageUser.controller.vo.ManageMenuVo;
import com.zifuji.cloud.server.manage.module.manageUser.service.ManageUserService;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.cglib.CglibUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ManageUserRunner implements ApplicationRunner {
	private ManageUserService manageUserService;
	private ManageUserEntityService manageUserEntityService;
	private ManageRoleEntityService manageRoleEntityService;
	private ManagePermissionEntityService managePermissionEntityService;
	private ManageMenuEntityService manageMenuEntityService;
	private ApplicationContext applicationContext;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		initMenu();
		initRole();
		initUser();
		initUserRoleRelation();
		initRolePermissionRelation();
		initRoleMenuRelation();
	}

	private void initMenu() {

		AddManageMenuMo m1 = new AddManageMenuMo();
		m1.setParentId(0L);
		m1.setName("首页");
		m1.setLabel("首页");
		m1.setPath("dashboard");
		m1.setComponent("/dashboard/dashboard");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper1 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper1.lambda().eq(ManageMenuEntity::getParentId, m1.getParentId())
				.eq(ManageMenuEntity::getPath, m1.getPath());
		ManageMenuEntity manageMenuEntity1 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper1);
		if (ObjectUtil.isNull(manageMenuEntity1)) {
			manageMenuEntity1 = new ManageMenuEntity();
			BeanUtil.copyProperties(m1, manageMenuEntity1);
			manageMenuEntityService.save(manageMenuEntity1);
		}

		AddManageMenuMo m2 = new AddManageMenuMo();
		m2.setParentId(0L);
		m2.setName("管理端用户");
		m2.setLabel("管理端用户");
		m2.setPath("manageUserGroup");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper2 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper2.lambda().eq(ManageMenuEntity::getParentId, m2.getParentId())
				.eq(ManageMenuEntity::getPath, m2.getPath());
		ManageMenuEntity manageMenuEntity2 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper2);
		if (ObjectUtil.isNull(manageMenuEntity2)) {
			manageMenuEntity2 = new ManageMenuEntity();
			BeanUtil.copyProperties(m2, manageMenuEntity2);
			manageMenuEntityService.save(manageMenuEntity2);
		}

		AddManageMenuMo m3 = new AddManageMenuMo();
		m3.setParentId(manageMenuEntity2.getTableId());
		m3.setName("用户列表");
		m3.setLabel("用户列表");
		m3.setPath("manageUserListView");
		m3.setComponent("/manageUser/manageUserList");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper3 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper3.lambda().eq(ManageMenuEntity::getParentId, m3.getParentId())
				.eq(ManageMenuEntity::getPath, m3.getPath());
		ManageMenuEntity manageMenuEntity3 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper3);
		if (ObjectUtil.isNull(manageMenuEntity3)) {
			manageMenuEntity3 = new ManageMenuEntity();
			BeanUtil.copyProperties(m3, manageMenuEntity3);
			manageMenuEntityService.save(manageMenuEntity3);
		}

		AddManageMenuMo m4 = new AddManageMenuMo();
		m4.setParentId(manageMenuEntity2.getTableId());
		m4.setName("角色列表");
		m4.setLabel("角色列表");
		m4.setPath("manageRoleListView");
		m4.setComponent("/manageUser/manageRoleList");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper4 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper4.lambda().eq(ManageMenuEntity::getParentId, m4.getParentId())
				.eq(ManageMenuEntity::getPath, m4.getPath());
		ManageMenuEntity manageMenuEntity4 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper4);
		if (ObjectUtil.isNull(manageMenuEntity4)) {
			manageMenuEntity4 = new ManageMenuEntity();
			BeanUtil.copyProperties(m4, manageMenuEntity4);
			manageMenuEntityService.save(manageMenuEntity4);
		}

		AddManageMenuMo m5 = new AddManageMenuMo();
		m5.setParentId(manageMenuEntity2.getTableId());
		m5.setName("接口列表");
		m5.setLabel("接口列表");
		m5.setPath("managePermissionListView");
		m5.setComponent("/manageUser/managePermissionList");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper5 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper5.lambda().eq(ManageMenuEntity::getParentId, m5.getParentId())
				.eq(ManageMenuEntity::getPath, m5.getPath());
		ManageMenuEntity manageMenuEntity5 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper5);
		if (ObjectUtil.isNull(manageMenuEntity5)) {
			manageMenuEntity5 = new ManageMenuEntity();
			BeanUtil.copyProperties(m5, manageMenuEntity5);
			manageMenuEntityService.save(manageMenuEntity5);
		}

		AddManageMenuMo m6 = new AddManageMenuMo();
		m6.setParentId(manageMenuEntity2.getTableId());
		m6.setName("菜单列表");
		m6.setLabel("菜单列表");
		m6.setPath("manageMenuListView");
		m6.setComponent("/manageUser/manageMenuList");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper6 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper6.lambda().eq(ManageMenuEntity::getParentId, m6.getParentId())
				.eq(ManageMenuEntity::getPath, m6.getPath());
		ManageMenuEntity manageMenuEntity6 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper6);
		if (ObjectUtil.isNull(manageMenuEntity6)) {
			manageMenuEntity6 = new ManageMenuEntity();
			BeanUtil.copyProperties(m6, manageMenuEntity6);
			manageMenuEntityService.save(manageMenuEntity6);
		}

		AddManageMenuMo m7 = new AddManageMenuMo();
		m7.setParentId(0L);
		m7.setName("个人页");
		m7.setLabel("个人页");
		m7.setPath("userAccount");
		m7.setComponent("/user/userAccount");
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper7 = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper7.lambda().eq(ManageMenuEntity::getParentId, m1.getParentId())
				.eq(ManageMenuEntity::getPath, m1.getPath());
		ManageMenuEntity manageMenuEntity7 = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper7);
		if (ObjectUtil.isNull(manageMenuEntity7)) {
			manageMenuEntity7 = new ManageMenuEntity();
			BeanUtil.copyProperties(m7, manageMenuEntity7);
			manageMenuEntityService.save(manageMenuEntity7);
		}

	}

	private void initRole() {
		try {
			AddManageRoleMo addManageRoleMo = new AddManageRoleMo();
			addManageRoleMo.setRoleCode("root");
			addManageRoleMo.setRoleName("root");
			manageUserService.addManageRole(addManageRoleMo);
		} catch (Exception e) {

		}
	}

	private void initUser() {
		try {
			AddManageUserMo addManageUserMo = new AddManageUserMo();
			addManageUserMo.setUserName("tc");
			addManageUserMo.setPassWord("123456");
			addManageUserMo.setShortName("tc");
			manageUserService.addManageUser(addManageUserMo);
		} catch (Exception e) {

		}
	}

	private void initUserRoleRelation() {
		try {
			QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<ManageRoleEntity>();
			manageRoleEntityQueryWrapper.lambda().eq(ManageRoleEntity::getRoleCode, "root");
			ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(manageRoleEntityQueryWrapper);
			QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
			manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, "tc");
			ManageUserEntity manageUserEntity = this.manageUserEntityService.getOne(manageUserEntityQueryWrapper);
			if (ObjectUtil.isNotNull(manageUserEntity) && ObjectUtil.isNotNull(manageRoleEntity)) {
				BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo = new BindUserAndRoleDelBeforeMo();
				bindUserAndRoleDelBeforeMo.setUserId(manageUserEntity.getTableId());
				List<Long> list = new ArrayList<Long>();
				list.add(manageRoleEntity.getTableId());
				bindUserAndRoleDelBeforeMo.setRoleIdList(list);
				manageUserService.bindUserAndRoleDelBefore(bindUserAndRoleDelBeforeMo);
			}
		} catch (Exception e) {

		}
	}

	private void initRolePermissionRelation() {
		try {
			QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<ManageRoleEntity>();
			manageRoleEntityQueryWrapper.lambda().eq(ManageRoleEntity::getRoleCode, "root");
			ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(manageRoleEntityQueryWrapper);
			List<ManagePermissionEntity> managePermissionEntityList = managePermissionEntityService.list();
			BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo = new BindRoleAndPermissionDelBeforeMo();
			bindRoleAndPermissionDelBeforeMo.setRoleId(manageRoleEntity.getTableId());
			List<Long> list = managePermissionEntityList.stream().map(ManagePermissionEntity::getTableId)
					.collect(Collectors.toList());
			bindRoleAndPermissionDelBeforeMo.setPermissionIdList(list);
			manageUserService.bindRoleAndPermissionDelBefore(bindRoleAndPermissionDelBeforeMo);
		} catch (Exception e) {

		}
	}

	private void initRoleMenuRelation() {
		try {
			QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<ManageRoleEntity>();
			manageRoleEntityQueryWrapper.lambda().eq(ManageRoleEntity::getRoleCode, "root");
			ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(manageRoleEntityQueryWrapper);
			List<ManageMenuEntity> manageMenuEntityList = manageMenuEntityService.list();
			BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo = new BindRoleAndMenuDelBeforeMo();
			bindRoleAndMenuDelBeforeMo.setRoleId(manageRoleEntity.getTableId());
			List<Long> list = manageMenuEntityList.stream().map(ManageMenuEntity::getTableId)
					.collect(Collectors.toList());
			bindRoleAndMenuDelBeforeMo.setMenuIdList(list);
			manageUserService.bindRoleAndMenuDelBefore(bindRoleAndMenuDelBeforeMo);
		} catch (Exception e) {

		}
	}

}
