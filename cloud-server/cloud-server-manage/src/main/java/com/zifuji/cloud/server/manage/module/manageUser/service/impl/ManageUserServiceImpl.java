package com.zifuji.cloud.server.manage.module.manageUser.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.base.bean.UserInfo;

import com.zifuji.cloud.server.base.util.RedisUtil;
import com.zifuji.cloud.server.manage.db.manageUser.entity.*;
import com.zifuji.cloud.server.manage.db.manageUser.service.*;

import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.vo.*;
import com.zifuji.cloud.server.manage.module.manageUser.mapper.ManageUserMapper;

import com.zifuji.cloud.server.manage.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.base.util.SecurityUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ManageUserServiceImpl implements ManageUserService {

	private ManageUserMapper manageUserMapper;
	private StringRedisTemplate stringRedisTemplate;
	private ManageUserEntityService manageUserEntityService;
	private ManageRoleEntityService manageRoleEntityService;
	private ManagePermissionEntityService managePermissionEntityService;
	private ManageMenuEntityService manageMenuEntityService;
	private ManageUserRoleEntityService manageUserRoleEntityService;
	private ManageRoleMenuEntityService manageRoleMenuEntityService;
	private ManageRolePermissionEntityService manageRolePermissionEntityService;

	@Override
	public DrawCaptchaVo drawCaptcha() {
		DrawCaptchaVo vo = new DrawCaptchaVo();
		String redisUuid = StrUtil.uuid();
		vo.setRedisUuid(redisUuid);
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
		RandomGenerator randomGenerator = new RandomGenerator(BaseConstant.STRING_C, 4);
		lineCaptcha.setGenerator(randomGenerator);
		lineCaptcha.createCode();
		vo.setImgBytes(lineCaptcha.getImageBytes());
		stringRedisTemplate.opsForValue().set(redisUuid, lineCaptcha.getCode(), 60 * 30, TimeUnit.SECONDS);
		return vo;
	}

	@Override
	public String login(LoginMo loginMo) {
		// 先校验验证码
		if (RedisUtil.equalsCodeAndValue(stringRedisTemplate, loginMo.getRedisUuid(), loginMo.getRedisValue())) {
			// 通过用户名查询数据库记录
			QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
			manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, loginMo.getUserName());
			ManageUserEntity manageUserEntity = manageUserEntityService.getOne(manageUserEntityQueryWrapper);
			if (ObjectUtil.isNotNull(manageUserEntity)) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if (bCryptPasswordEncoder.matches(loginMo.getPassWord(), manageUserEntity.getPassWord())) {
					return getLoginToken(manageUserEntity);
				} else {
					throw new Exception20000("用户名密码错误");
				}
			} else {
				throw new Exception20000("用户名密码错误");
			}
		} else {
			throw new Exception20000("请输入正确的验证码");
		}
	}

	private String getLoginToken(ManageUserEntity manageUserEntity) {
		String token = StrUtil.uuid();
		UserInfo userInfo = new UserInfo();
		BeanUtil.copyProperties(manageUserEntity, userInfo);
		List<String> roleCodeList = new ArrayList<>();
		List<String> permissionCodeList = new ArrayList<>();
		QueryWrapper<ManageUserRoleEntity> manageUserRoleEntityQueryWrapper = new QueryWrapper<>();
		manageUserRoleEntityQueryWrapper.lambda().eq(ManageUserRoleEntity::getUserId, userInfo.getTableId());
		List<ManageUserRoleEntity> manageUserRoleEntityList = manageUserRoleEntityService
				.list(manageUserRoleEntityQueryWrapper);
		if (ObjectUtil.isNotEmpty(manageUserRoleEntityList)) {
			List<ManageRoleEntity> manageRoleEntityList = manageRoleEntityService.listByIds(manageUserRoleEntityList
					.stream().map(ManageUserRoleEntity::getRoleId).collect(Collectors.toList()));
			if (ObjectUtil.isNotEmpty(manageRoleEntityList)) {
				roleCodeList = manageRoleEntityList.stream().map(ManageRoleEntity::getRoleCode)
						.collect(Collectors.toList());
				QueryWrapper<ManageRolePermissionEntity> manageRolePermissionEntityQueryWrapper = new QueryWrapper<>();
				manageRolePermissionEntityQueryWrapper.lambda().in(ManageRolePermissionEntity::getRoleId,
						manageRoleEntityList.stream().map(ManageRoleEntity::getTableId).collect(Collectors.toList()));
				List<ManageRolePermissionEntity> manageRolePermissionEntityList = manageRolePermissionEntityService
						.list(manageRolePermissionEntityQueryWrapper);
				if (ObjectUtil.isNotEmpty(manageRolePermissionEntityList)) {
					List<ManagePermissionEntity> managePermissionEntityList = managePermissionEntityService
							.listByIds(manageRolePermissionEntityList.stream()
									.map(ManageRolePermissionEntity::getPermissionId).collect(Collectors.toList()));
					if (ObjectUtil.isNotEmpty(managePermissionEntityList)) {
						permissionCodeList = managePermissionEntityList.stream().map(managePermissionEntity -> {
							return managePermissionEntity.getCodeSys() + ":" + managePermissionEntity.getCodeModule()
									+ ":" + managePermissionEntity.getCodeMethod();
						}).collect(Collectors.toList());
					}
				}
			}
		}
		userInfo.setRoleCodeList(roleCodeList);
		userInfo.setPermissionCodeList(permissionCodeList);
		userInfo.setToken(token);
		stringRedisTemplate.opsForValue().set(token, JSONObject.toJSONString(userInfo), 60 * 30, TimeUnit.SECONDS);
		return token;
	}

	@Override
	public List<ManageMenuVo> getMenu() {
		List<ManageMenuVo> manageMenuServiceVoList = new ArrayList<>();
		UserInfo userInfo = SecurityUtil.getUserDetails();
		if (ObjectUtil.isNotEmpty(userInfo.getRoleCodeList())) {
			QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<>();
			manageRoleEntityQueryWrapper.lambda().in(ManageRoleEntity::getRoleCode, userInfo.getRoleCodeList());
			List<ManageRoleEntity> manageRoleEntityList = manageRoleEntityService.list(manageRoleEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(manageRoleEntityList)) {
				QueryWrapper<ManageRoleMenuEntity> manageRoleMenuEntityQueryWrapper = new QueryWrapper<>();
				manageRoleMenuEntityQueryWrapper.lambda().in(ManageRoleMenuEntity::getRoleId,
						manageRoleEntityList.stream().map(ManageRoleEntity::getTableId).collect(Collectors.toList()));
				List<ManageRoleMenuEntity> manageRoleMenuEntityList = manageRoleMenuEntityService
						.list(manageRoleMenuEntityQueryWrapper);
				if (ObjectUtil.isNotEmpty(manageRoleMenuEntityList)) {
					QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper = new QueryWrapper<>();
					manageMenuEntityQueryWrapper.lambda()
							.in(ManageMenuEntity::getTableId,
									manageRoleMenuEntityList.stream().map(ManageRoleMenuEntity::getMenuId)
											.collect(Collectors.toList()))
							.orderByAsc(ManageMenuEntity::getSortNum).orderByDesc(ManageMenuEntity::getCreateTime);
					List<ManageMenuEntity> manageMenuEntityList = manageMenuEntityService
							.list(manageMenuEntityQueryWrapper);
					if (ObjectUtil.isNotEmpty(manageMenuEntityList)) {
						manageMenuServiceVoList = manageMenuEntityList.stream().map(manageMenuEntity -> {
							ManageMenuVo vo = new ManageMenuVo();
							BeanUtil.copyProperties(manageMenuEntity, vo);
							return vo;
						}).collect(Collectors.toList());
					}
				}
			}
		} else {
			QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper = new QueryWrapper<>();
			manageMenuEntityQueryWrapper.lambda().eq(ManageMenuEntity::getParentId, 0L)
					.eq(ManageMenuEntity::getPath, "dashboard").orderByAsc(ManageMenuEntity::getSortNum)
					.orderByDesc(ManageMenuEntity::getCreateTime);
			List<ManageMenuEntity> manageMenuEntityList = manageMenuEntityService.list(manageMenuEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(manageMenuEntityList)) {
				manageMenuServiceVoList = manageMenuEntityList.stream().map(manageMenuEntity -> {
					ManageMenuVo vo = new ManageMenuVo();
					BeanUtil.copyProperties(manageMenuEntity, vo);
					return vo;
				}).collect(Collectors.toList());
			}
		}
		return manageMenuServiceVoList;
	}

	@Override
	public IPage<ManageUserVo> queryPageManageUser(QueryPageManageUserQo queryPageManageUserQo) {
		Page<ManageUserEntity> page = new Page<>(queryPageManageUserQo.getCurrent(), queryPageManageUserQo.getSize());
		QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotBlank(queryPageManageUserQo.getUserName())) {
			manageUserEntityQueryWrapper.lambda().like(ManageUserEntity::getUserName,
					queryPageManageUserQo.getUserName());
		}
		if (StrUtil.isNotBlank(queryPageManageUserQo.getShortName())) {
			manageUserEntityQueryWrapper.lambda().like(ManageUserEntity::getShortName,
					queryPageManageUserQo.getShortName());
		}
		page = manageUserEntityService.page(page, manageUserEntityQueryWrapper);
		return page.convert(manageUserEntity -> {
			ManageUserVo queryPageManageUserVo = new ManageUserVo();
			BeanUtil.copyProperties(manageUserEntity, queryPageManageUserVo);
			return queryPageManageUserVo;
		});
	}

	@Override
	public ManageUserVo getManageUserById(Long id) {
		ManageUserEntity manageUserEntity = manageUserEntityService.getById(id);
		ManageUserVo ManageUserVo = new ManageUserVo();
		BeanUtil.copyProperties(manageUserEntity, ManageUserVo);
		return ManageUserVo;
	}

	@Override
	public ManageUserVo addManageUser(AddManageUserMo addManageUserMo) {
		QueryWrapper<ManageUserEntity> manageUserEntityQueryWrapper = new QueryWrapper<>();
		manageUserEntityQueryWrapper.lambda().eq(ManageUserEntity::getUserName, addManageUserMo.getUserName());
		ManageUserEntity manageUserEntity = this.manageUserEntityService.getOne(manageUserEntityQueryWrapper);
		if (ObjectUtil.isNotNull(manageUserEntity)) {
			throw new Exception20000("用户名重复");
		}
		manageUserEntity = new ManageUserEntity();
		manageUserEntity.setUserName(addManageUserMo.getUserName());
		// 密码加密
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		manageUserEntity.setPassWord(bCryptPasswordEncoder.encode(addManageUserMo.getPassWord()));
		manageUserEntity.setShortName(addManageUserMo.getShortName());
		manageUserEntityService.save(manageUserEntity);
		ManageUserVo ManageUserVo = new ManageUserVo();
		BeanUtil.copyProperties(manageUserEntity, ManageUserVo);
		return ManageUserVo;
	}

	@Override
	public ManageUserVo updateManageUser(UpdateManageUserMo updateManageUserMo) {
		ManageUserEntity manageUserEntity = manageUserEntityService.getById(updateManageUserMo.getTableId());
		if (ObjectUtil.isNull(manageUserEntity)) {
			throw new Exception20000("编辑数据错误");
		}
		manageUserEntity.setUserName(updateManageUserMo.getUserName());
		manageUserEntity.setShortName(updateManageUserMo.getShortName());
		manageUserEntityService.updateById(manageUserEntity);
		ManageUserVo ManageUserVo = new ManageUserVo();
		BeanUtil.copyProperties(manageUserEntity, ManageUserVo);
		return ManageUserVo;
	}

	@Override
	public List<ManageRoleVo> queryListManageRole(QueryPageManageRoleQo queryPageManageRoleQo) {
		QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(queryPageManageRoleQo.getUserId())) {
			QueryWrapper<ManageUserRoleEntity> manageUserRoleEntityQueryWrapper = new QueryWrapper<>();
			manageUserRoleEntityQueryWrapper.lambda().eq(ManageUserRoleEntity::getUserId,
					queryPageManageRoleQo.getUserId());
			List<ManageUserRoleEntity> manageUserRoleEntityList = manageUserRoleEntityService
					.list(manageUserRoleEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(manageUserRoleEntityList)) {
				manageRoleEntityQueryWrapper.lambda().in(ManageRoleEntity::getTableId, manageUserRoleEntityList.stream()
						.map(ManageUserRoleEntity::getRoleId).collect(Collectors.toList()));
			} else {
				return new ArrayList<>();
			}
		}
		if (StrUtil.isNotBlank(queryPageManageRoleQo.getRoleCode())) {
			manageRoleEntityQueryWrapper.lambda().eq(ManageRoleEntity::getRoleCode,
					queryPageManageRoleQo.getRoleCode());
		}
		manageRoleEntityQueryWrapper.lambda().orderByAsc(ManageRoleEntity::getSortNum)
				.orderByDesc(ManageRoleEntity::getCreateTime);
		List<ManageRoleEntity> manageRoleEntityList = manageRoleEntityService.list(manageRoleEntityQueryWrapper);
		return manageRoleEntityList.stream().map(manageRoleEntity -> {
			ManageRoleVo vo = new ManageRoleVo();
			BeanUtil.copyProperties(manageRoleEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public IPage<ManageRoleVo> queryPageManageRole(QueryPageManageRoleQo queryPageManageRoleQo) {
		Page<ManageRoleEntity> page = new Page<>(queryPageManageRoleQo.getCurrent(), queryPageManageRoleQo.getSize());
		QueryWrapper<ManageRoleEntity> manageRoleEntityQueryWrapper = new QueryWrapper<>();

		page = manageRoleEntityService.page(page, manageRoleEntityQueryWrapper);
		return page.convert(manageRoleEntity -> {
			ManageRoleVo vo = new ManageRoleVo();
			BeanUtil.copyProperties(manageRoleEntity, vo);
			return vo;
		});
	}

	@Override
	public ManageRoleVo getManageRoleById(Long id) {
		ManageRoleEntity manageRoleEntity = manageRoleEntityService.getById(id);
		ManageRoleVo vo = new ManageRoleVo();
		BeanUtil.copyProperties(manageRoleEntity, vo);
		return vo;
	}

	@Override
	public ManageRoleVo addManageRole(AddManageRoleMo addManageRoleMo) {
		QueryWrapper<ManageRoleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ManageRoleEntity::getRoleCode, addManageRoleMo.getRoleCode());
		ManageRoleEntity manageRoleEntity = manageRoleEntityService.getOne(queryWrapper);
		if (ObjectUtil.isNotNull(manageRoleEntity)) {
			throw new Exception20000("角色编码不能重复");
		}
		manageRoleEntity = new ManageRoleEntity();
		manageRoleEntity.setRoleName(addManageRoleMo.getRoleName());
		manageRoleEntity.setRoleCode(addManageRoleMo.getRoleCode());
		manageRoleEntity.setRoleDescription(addManageRoleMo.getRoleDescription());
		manageRoleEntityService.save(manageRoleEntity);
		ManageRoleVo vo = new ManageRoleVo();
		BeanUtil.copyProperties(manageRoleEntity, vo);
		return vo;
	}

	@Override
	public ManageRoleVo updateManageRole(UpdateManageRoleMo updateManageRoleMo) {
		ManageRoleEntity manageRoleEntity = manageRoleEntityService.getById(updateManageRoleMo.getTableId());
		if (ObjectUtil.isNull(manageRoleEntity)) {
			throw new Exception20000("");
		}
		if (!StrUtil.equals(manageRoleEntity.getRoleCode(), updateManageRoleMo.getRoleCode())) {
			throw new Exception20000("");
		}
		manageRoleEntity.setRoleDescription(updateManageRoleMo.getRoleDescription());
		ManageRoleVo vo = new ManageRoleVo();
		BeanUtil.copyProperties(manageRoleEntity, vo);
		return vo;
	}

	@Override
	public List<ManagePermissionVo> queryListManagePermission(QueryPageManagePermissionQo queryPageManagePermissionQo) {
		QueryWrapper<ManagePermissionEntity> managePermissionEntityQueryWrapper = new QueryWrapper<>();
		if (ObjectUtil.isNotNull(queryPageManagePermissionQo.getRoleId())) {
			QueryWrapper<ManageRolePermissionEntity> manageRolePermissionEntityQueryWrapper = new QueryWrapper<>();
			manageRolePermissionEntityQueryWrapper.lambda().eq(ManageRolePermissionEntity::getRoleId,
					queryPageManagePermissionQo.getRoleId());
			List<ManageRolePermissionEntity> manageRolePermissionEntityList = manageRolePermissionEntityService
					.list(manageRolePermissionEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(manageRolePermissionEntityList)) {
				managePermissionEntityQueryWrapper.lambda().in(ManagePermissionEntity::getTableId,
						manageRolePermissionEntityList.stream().map(ManageRolePermissionEntity::getPermissionId)
								.collect(Collectors.toList()));
			} else {
				return new ArrayList<>();
			}
		}
		List<ManagePermissionEntity> managePermissionEntityList = managePermissionEntityService
				.list(managePermissionEntityQueryWrapper);
		return managePermissionEntityList.stream().map(managePermissionEntity -> {
			ManagePermissionVo vo = new ManagePermissionVo();
			BeanUtil.copyProperties(managePermissionEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public List<ManageMenuVo> queryListManageMenu(QueryPageManageMenuQo queryPageManageMenuQo) {
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotBlank(queryPageManageMenuQo.getRoleId())) {
			QueryWrapper<ManageRoleMenuEntity> manageRoleMenuEntityQueryWrapper = new QueryWrapper<>();
			manageRoleMenuEntityQueryWrapper.lambda().eq(ManageRoleMenuEntity::getRoleId,
					queryPageManageMenuQo.getRoleId());
			List<ManageRoleMenuEntity> manageRoleMenuEntityList = manageRoleMenuEntityService
					.list(manageRoleMenuEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(manageRoleMenuEntityList)) {
				manageMenuEntityQueryWrapper.lambda().in(ManageMenuEntity::getTableId, manageRoleMenuEntityList.stream()
						.map(ManageRoleMenuEntity::getMenuId).collect(Collectors.toList()));
			} else {
				return new ArrayList<>();
			}
		}
		manageMenuEntityQueryWrapper.lambda().orderByAsc(ManageMenuEntity::getSortNum)
				.orderByDesc(ManageMenuEntity::getTableId);
		List<ManageMenuEntity> manageMenuEntityList = manageMenuEntityService.list(manageMenuEntityQueryWrapper);
		return manageMenuEntityList.stream().map(manageMenuEntity -> {
			ManageMenuVo vo = new ManageMenuVo();
			BeanUtil.copyProperties(manageMenuEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public IPage<ManagePermissionVo> queryPageManagePermission(
			QueryPageManagePermissionQo queryPageManagePermissionQo) {

		Page<ManagePermissionEntity> page = new Page<>(queryPageManagePermissionQo.getCurrent(),
				queryPageManagePermissionQo.getSize());

		QueryWrapper<ManagePermissionEntity> managePermissionEntityQueryWrapper = new QueryWrapper<>();

		page = managePermissionEntityService.page(page, managePermissionEntityQueryWrapper);
		return page.convert(managePermissionEntity -> {
			ManagePermissionVo vo = new ManagePermissionVo();
			BeanUtil.copyProperties(managePermissionEntity, vo);
			return vo;
		});
	}

	@Override
	public ManagePermissionVo addManagePermission(AddManagePermissionMo addManagePermissionMo) {
		QueryWrapper<ManagePermissionEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ManagePermissionEntity::getCodeSys, addManagePermissionMo.getCodeSys())
				.eq(ManagePermissionEntity::getCodeModule, addManagePermissionMo.getCodeModule())
				.eq(ManagePermissionEntity::getCodeMethod, addManagePermissionMo.getCodeMethod());
		ManagePermissionEntity entity = managePermissionEntityService.getOne(queryWrapper);
		if (ObjectUtil.isNull(entity)) {
			entity = new ManagePermissionEntity();
			BeanUtil.copyProperties(addManagePermissionMo, entity);
			managePermissionEntityService.save(entity);
		} else {
			throw new Exception20000("有问题");
		}
		ManagePermissionVo vo = new ManagePermissionVo();
		BeanUtil.copyProperties(entity, vo);
		return vo;
	}

	@Override
	public ManagePermissionVo updateManagePermission(UpdateManagePermissionMo updateManagePermissionMo) {
		ManagePermissionEntity managePermissionEntity = managePermissionEntityService
				.getById(updateManagePermissionMo.getTableId());
		if (ObjectUtil.isNull(managePermissionEntity)) {
			throw new Exception20000("有问题");
		} else {
			managePermissionEntity.setPerName(updateManagePermissionMo.getPerName());
			managePermissionEntity.setPerDescription(updateManagePermissionMo.getPerDescription());
			managePermissionEntityService.save(managePermissionEntity);
		}
		ManagePermissionVo vo = new ManagePermissionVo();
		BeanUtil.copyProperties(managePermissionEntity, vo);
		return vo;
	}

	@Override
	public Boolean delManagePermission(Long id) {
		return managePermissionEntityService.removeById(id);
	}

	@Override
	public IPage<ManageMenuVo> queryPageManageMenu(QueryPageManageMenuQo queryPageManageMenuQo) {
		Page<ManageMenuEntity> page = new Page<>();
		page.setCurrent(queryPageManageMenuQo.getCurrent());
		page.setSize(queryPageManageMenuQo.getSize());
		QueryWrapper<ManageMenuEntity> manQueryWrapper = new QueryWrapper<ManageMenuEntity>();

		page = manageMenuEntityService.page(page, manQueryWrapper);
		return page.convert(manageMenuEntity -> {
			ManageMenuVo vo = new ManageMenuVo();
			BeanUtil.copyProperties(manageMenuEntity, vo);
			return vo;
		});
	}

	@Override
	public Boolean delManageUser(Long id) {
		ManageUserEntity manageUserEntity = manageUserEntityService.getById(id);
		if (ObjectUtil.isNull(manageUserEntity)) {
			throw new Exception20000("数据错误");
		}
		return manageUserEntityService.removeById(id);
	}

	@Override
	public List<ManageUserVo> queryListManageUser(QueryPageManageUserQo queryPageManageUserQo) {
		QueryWrapper<ManageUserEntity> manageUserQueryWrapper = new QueryWrapper<ManageUserEntity>();

		List<ManageUserEntity> list = manageUserEntityService.list(manageUserQueryWrapper);

		return list.stream().map(manageUserEntity -> {
			ManageUserVo vo = new ManageUserVo();
			BeanUtil.copyProperties(manageUserEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	@Override
	public Boolean delManageRole(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagePermissionVo getManagePermissionById(Long id) {
		ManagePermissionEntity managePermissionEntity = managePermissionEntityService.getById(id);
		if (ObjectUtil.isNull(managePermissionEntity)) {
			throw new Exception20000("数据错误");
		} else {
			ManagePermissionVo vo = new ManagePermissionVo();
			BeanUtil.copyProperties(managePermissionEntity, vo);
			return vo;
		}

	}

	@Override
	public ManageMenuVo addManageMenu(AddManageMenuMo addManageMenuMo) {
		QueryWrapper<ManageMenuEntity> manageMenuEntityQueryWrapper = new QueryWrapper<ManageMenuEntity>();
		manageMenuEntityQueryWrapper.lambda().eq(ManageMenuEntity::getParentId, addManageMenuMo.getParentId())
				.eq(ManageMenuEntity::getPath, addManageMenuMo.getPath());

		ManageMenuEntity manageMenuEntity = manageMenuEntityService.getOne(manageMenuEntityQueryWrapper);
		if (ObjectUtil.isNull(manageMenuEntity)) {
			manageMenuEntity = new ManageMenuEntity();
			BeanUtil.copyProperties(addManageMenuMo, manageMenuEntity);
			manageMenuEntityService.save(manageMenuEntity);
		} else {
			throw new Exception20000("数据有问题");
		}
		ManageMenuVo vo = new ManageMenuVo();
		BeanUtil.copyProperties(manageMenuEntity, vo);
		return vo;
	}

	@Override
	public Boolean delManageMenu(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageMenuVo updateManageMenu(UpdateManageMenuMo updateManageMenuMo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManageMenuVo getManageMenuById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean logout() {
		UserInfo userInfo = SecurityUtil.getUserDetails();
		return stringRedisTemplate.delete(userInfo.getToken());
	}

	@Override
	public Boolean bindUserAndRoleDelBefore(BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo) {
		QueryWrapper<ManageUserRoleEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.lambda().eq(ManageUserRoleEntity::getUserId, bindUserAndRoleDelBeforeMo.getUserId());
		manageUserRoleEntityService.remove(queryWrapper);
		if (ObjectUtil.isNotEmpty(bindUserAndRoleDelBeforeMo.getRoleIdList())) {
			List<ManageUserRoleEntity> list = bindUserAndRoleDelBeforeMo.getRoleIdList().stream().map(s -> {
				ManageUserRoleEntity manageUserRoleEntity = new ManageUserRoleEntity();
				manageUserRoleEntity.setUserId(bindUserAndRoleDelBeforeMo.getUserId());
				manageUserRoleEntity.setRoleId(s);
				return manageUserRoleEntity;
			}).collect(Collectors.toList());
			manageUserRoleEntityService.saveBatch(list);
		}
		return true;
	}

	@Override
	public Boolean bindRoleAndPermissionDelBefore(BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo) {
		QueryWrapper<ManageRolePermissionEntity> manageRolePermissionEntityQueryWrapper = new QueryWrapper<>();
		manageRolePermissionEntityQueryWrapper.lambda().eq(ManageRolePermissionEntity::getRoleId,
				bindRoleAndPermissionDelBeforeMo.getRoleId());
		manageRolePermissionEntityService.remove(manageRolePermissionEntityQueryWrapper);
		if (ObjectUtil.isNotEmpty(bindRoleAndPermissionDelBeforeMo.getPermissionIdList())) {
			List<ManageRolePermissionEntity> list = bindRoleAndPermissionDelBeforeMo.getPermissionIdList().stream()
					.map(s -> {
						ManageRolePermissionEntity manageRolePermissionEntity = new ManageRolePermissionEntity();
						manageRolePermissionEntity.setRoleId(bindRoleAndPermissionDelBeforeMo.getRoleId());
						manageRolePermissionEntity.setPermissionId(s);
						return manageRolePermissionEntity;
					}).collect(Collectors.toList());
			manageRolePermissionEntityService.saveBatch(list);
		}
		return true;
	}

	@Override
	public Boolean bindRoleAndMenuDelBefore(BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo) {
		QueryWrapper<ManageRoleMenuEntity> manageRoleMenuEntityQueryWrapper = new QueryWrapper<>();
		manageRoleMenuEntityQueryWrapper.lambda().eq(ManageRoleMenuEntity::getRoleId,
				bindRoleAndMenuDelBeforeMo.getRoleId());
		manageRoleMenuEntityService.remove(manageRoleMenuEntityQueryWrapper);
		if (ObjectUtil.isNotEmpty(bindRoleAndMenuDelBeforeMo.getMenuIdList())) {
			List<ManageRoleMenuEntity> list = bindRoleAndMenuDelBeforeMo.getMenuIdList().stream().map(s -> {
				ManageRoleMenuEntity manageRoleMenuEntity = new ManageRoleMenuEntity();
				manageRoleMenuEntity.setRoleId(bindRoleAndMenuDelBeforeMo.getRoleId());
				manageRoleMenuEntity.setMenuId(s);
				return manageRoleMenuEntity;
			}).collect(Collectors.toList());
			manageRoleMenuEntityService.saveBatch(list);
		}
		return true;
	}

	@Override
	public Boolean resetPassWord(ResetPassWordMo resetPassWordMo) {
		ManageUserEntity manageUserEntity = manageUserEntityService.getById(resetPassWordMo.getTableId());
		if (ObjectUtil.isNull(manageUserEntity)) {
			throw new Exception20000("用户不存在");
		}
		// 密码加密
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		manageUserEntity.setPassWord(bCryptPasswordEncoder.encode(resetPassWordMo.getPassWord()));
		manageUserEntityService.updateById(manageUserEntity);
		return true;
	}
}
