package com.zifuji.cloud.server.business.module.webUser.service.impl;

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
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.server.base.util.RedisUtil;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import com.zifuji.cloud.server.business.db.user.entity.*;
import com.zifuji.cloud.server.business.db.user.service.*;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.LoginMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.RegisterMo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebMenuQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebRoleQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebUserQo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.*;
import com.zifuji.cloud.server.business.module.webUser.mapper.WebUserMapper;
import com.zifuji.cloud.server.business.module.webUser.service.WebUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class WebUserServiceImpl implements WebUserService {

	private WebUserMapper webUserMapper;
	private WebUserEntityService webUserEntityService;
	private WebUserRoleEntityService webUserRoleEntityService;
	private WebRoleEntityService webRoleEntityService;
	private WebRoleMenuEntityService webRoleMenuEntityService;
	private WebMenuEntityService webMenuEntityService;
	private WebRolePermissionEntityService webRolePermissionEntityService;
	private WebPermissionEntityService webPermissionEntityService;
	private WebPeopleEntityService webPeopleEntityService;
	private WebCompanyEntityService webCompanyEntityService;
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public List<WebMenuVo> getMenu() {
		List<WebMenuVo> webMenuServiceVoList = new ArrayList<>();
		UserInfo userInfo = SecurityUtil.getUserDetails();
		if (ObjectUtil.isNotEmpty(userInfo.getRoleCodeList())) {
			QueryWrapper<WebRoleEntity> webRoleEntityQueryWrapper = new QueryWrapper<>();
			webRoleEntityQueryWrapper.lambda().in(WebRoleEntity::getCode, userInfo.getRoleCodeList());
			List<WebRoleEntity> webRoleEntityList = webRoleEntityService.list(webRoleEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(webRoleEntityList)) {
				QueryWrapper<WebRoleMenuEntity> webRoleMenuEntityQueryWrapper = new QueryWrapper<>();
				List<Long> roleIdList = webRoleEntityList.stream().map(WebRoleEntity::getTableId)
						.collect(Collectors.toList());
				webRoleMenuEntityQueryWrapper.lambda().in(WebRoleMenuEntity::getRoleId, roleIdList);
				List<WebRoleMenuEntity> webRoleMenuEntityList = webRoleMenuEntityService
						.list(webRoleMenuEntityQueryWrapper);
				if (ObjectUtil.isNotEmpty(webRoleMenuEntityList)) {
					QueryWrapper<WebMenuEntity> webMenuEntityQueryWrapper = new QueryWrapper<>();
					List<String> menuIdList = webRoleMenuEntityList.stream().map(WebRoleMenuEntity::getMenuId)
							.collect(Collectors.toList());
					webMenuEntityQueryWrapper.lambda().in(WebMenuEntity::getTableId, menuIdList)
							.orderByAsc(WebMenuEntity::getSortNum).orderByDesc(WebMenuEntity::getCreateTime);
					List<WebMenuEntity> webMenuEntityList = webMenuEntityService.list(webMenuEntityQueryWrapper);
					if (ObjectUtil.isNotEmpty(webMenuEntityList)) {
						webMenuServiceVoList = webMenuEntityList.stream().map(webMenuEntity -> {
							WebMenuVo vo = new WebMenuVo();
							BeanUtil.copyProperties(webMenuEntity, vo);
							return vo;
						}).collect(Collectors.toList());
					}
				}
			}
		}
		return webMenuServiceVoList;
	}

	@Override
	public DrawCaptchaVo drawCaptcha() {
		DrawCaptchaVo drawCaptchaVo = new DrawCaptchaVo();
		String redisUuid = StrUtil.uuid();
		drawCaptchaVo.setRedisUuid(redisUuid);
		LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
		RandomGenerator randomGenerator = new RandomGenerator(BaseConstant.STRING_C, 4);
		lineCaptcha.setGenerator(randomGenerator);
		lineCaptcha.createCode();
		drawCaptchaVo.setImgBytes(lineCaptcha.getImageBytes());
		stringRedisTemplate.opsForValue().set(redisUuid, lineCaptcha.getCode(), 60 * 30, TimeUnit.SECONDS);
		return drawCaptchaVo;
	}

	@Override
	public String login(LoginMo loginMo) {
		// 先校验验证码
		if (RedisUtil.equalsCodeAndValue(stringRedisTemplate, loginMo.getRedisUuid(), loginMo.getRedisValue())) {
			// 通过用户名查询数据库记录
			QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
			webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, loginMo.getUserName());
			WebUserEntity webUserEntity = webUserEntityService.getOne(webUserEntityQueryWrapper);
			if (ObjectUtil.isNotNull(webUserEntity)) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if (bCryptPasswordEncoder.matches(loginMo.getPassWord(), webUserEntity.getPassWord())) {
					return getLoginToken(webUserEntity);
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

	@Override
	public String register(RegisterMo registerMo) {
		if (RedisUtil.equalsCodeAndValue(stringRedisTemplate, registerMo.getRedisUuid(), registerMo.getRedisValue())) {
			QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
			webUserEntityQueryWrapper.lambda().eq(WebUserEntity::getUserName, registerMo.getUserName());
			WebUserEntity webUserEntity = webUserEntityService.getOne(webUserEntityQueryWrapper);
			if (ObjectUtil.isNull(webUserEntity)) {
				webUserEntity = new WebUserEntity();
				webUserEntity.setUserName(registerMo.getUserName());
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				webUserEntity.setPassWord(bCryptPasswordEncoder.encode(registerMo.getPassWord()));
				webUserEntityService.save(webUserEntity);
				List<String> roleCodeList = Collections.singletonList("register");
				bindUserAndRoleByUserIdAndRoleCodeDelBefore(webUserEntity.getTableId(), roleCodeList);
				return getLoginToken(webUserEntity);
			} else {
				throw new Exception20000("当前用户名已存在");
			}
		} else {
			throw new Exception20000("请输入正确的验证码");
		}
	}

	@Override
	public WebUserVo getMyselfInfo() {
		UserInfo userInfo = SecurityUtil.getUserDetails();
		WebUserEntity webUserEntity = webUserEntityService.getById(userInfo.getTableId());
		WebUserVo vo = new WebUserVo();
		BeanUtil.copyProperties(webUserEntity, vo);
		return vo;
	}

	@Override
	public Boolean logout() {
		UserInfo userInfo = SecurityUtil.getUserDetails();
		if (StrUtil.isNotBlank(userInfo.getToken())) {
			stringRedisTemplate.delete(userInfo.getToken());
		}
		return true;
	}

	@Override
	public IPage<WebUserVo> queryPageWebUser(QueryWebUserQo queryWebUserQo) {
		Page<WebUserEntity> page = new Page<>(queryWebUserQo.getCurrent(), queryWebUserQo.getSize());
		QueryWrapper<WebUserEntity> webUserEntityQueryWrapper = new QueryWrapper<>();
		page = webUserEntityService.page(page, webUserEntityQueryWrapper);
		return page.convert(webUserEntity -> {
			WebUserVo webUserVo = new WebUserVo();
			BeanUtil.copyProperties(webUserEntity, webUserVo);
			return webUserVo;
		});
	}

	@Override
	public IPage<WebRoleVo> queryPageWebRole(QueryWebRoleQo queryWebRoleQo) {
		Page<WebRoleEntity> page = new Page<>(queryWebRoleQo.getCurrent(), queryWebRoleQo.getSize());

		QueryWrapper<WebRoleEntity> webRoleEntityQueryWrapper = new QueryWrapper<>();
		page = webRoleEntityService.page(page, webRoleEntityQueryWrapper);

		return page.convert(webRoleEntity -> {
			WebRoleVo vo = new WebRoleVo();
			BeanUtil.copyProperties(webRoleEntity, vo);
			return vo;
		});
	}

	@Override
	public List<WebMenuVo> queryListWebMenu(QueryWebMenuQo queryWebMenuQo) {

		QueryWrapper<WebMenuEntity> webMenuEntityQueryWrapper = new QueryWrapper<>();
		if (StrUtil.isNotBlank(queryWebMenuQo.getRoleId())) {
			QueryWrapper<WebRoleMenuEntity> webRoleMenuEntityQueryWrapper = new QueryWrapper<>();
			webRoleMenuEntityQueryWrapper.lambda().eq(WebRoleMenuEntity::getRoleId, queryWebMenuQo.getRoleId());
			List<WebRoleMenuEntity> webRoleMenuEntityList = webRoleMenuEntityService
					.list(webRoleMenuEntityQueryWrapper);
			if (ObjectUtil.isNotEmpty(webRoleMenuEntityList)) {
				webMenuEntityQueryWrapper.lambda().in(WebMenuEntity::getTableId,
						webRoleMenuEntityList.stream().map(WebRoleMenuEntity::getMenuId).collect(Collectors.toList()));
			} else {
				return new ArrayList<>();
			}
		}
		List<WebMenuEntity> webMenuEntityList = webMenuEntityService.list(webMenuEntityQueryWrapper);

		return webMenuEntityList.stream().map(webMenuEntity -> {
			WebMenuVo vo = new WebMenuVo();
			BeanUtil.copyProperties(webMenuEntity, vo);
			return vo;
		}).collect(Collectors.toList());
	}

	private String getLoginToken(WebUserEntity webUserEntity) {
		String token = StrUtil.uuid();
		UserInfo userInfo = new UserInfo();
		BeanUtil.copyProperties(webUserEntity, userInfo);
		List<String> roleCodeList = new ArrayList<String>();
		List<String> permissionCodeList = new ArrayList<String>();
		QueryWrapper<WebUserRoleEntity> webUserRoleEntityQueryWrapper = new QueryWrapper<>();
		webUserRoleEntityQueryWrapper.lambda().eq(WebUserRoleEntity::getUserId, userInfo.getTableId());
		List<WebUserRoleEntity> webUserRoleEntityList = webUserRoleEntityService.list(webUserRoleEntityQueryWrapper);
		if (ObjectUtil.isNotEmpty(webUserRoleEntityList)) {
			List<WebRoleEntity> webRoleEntityList = webRoleEntityService.listByIds(
					webUserRoleEntityList.stream().map(WebUserRoleEntity::getRoleId).collect(Collectors.toList()));
			if (ObjectUtil.isNotEmpty(webRoleEntityList)) {
				roleCodeList = webRoleEntityList.stream().map(WebRoleEntity::getCode).collect(Collectors.toList());
				QueryWrapper<WebRolePermissionEntity> webRolePermissionEntityQueryWrapper = new QueryWrapper<>();
				webRolePermissionEntityQueryWrapper.lambda().in(WebRolePermissionEntity::getRoleId,
						webRoleEntityList.stream().map(WebRoleEntity::getTableId).collect(Collectors.toList()));
				List<WebRolePermissionEntity> webRolePermissionEntityList = webRolePermissionEntityService
						.list(webRolePermissionEntityQueryWrapper);
				if (ObjectUtil.isNotEmpty(webRolePermissionEntityList)) {
					List<WebPermissionEntity> webPermissionEntityList = webPermissionEntityService
							.listByIds(webRolePermissionEntityList.stream()
									.map(WebRolePermissionEntity::getPermissionId).collect(Collectors.toList()));
					if (ObjectUtil.isNotEmpty(webPermissionEntityList)) {
						permissionCodeList = webPermissionEntityList.stream().map(webPermissionEntity -> {
							return webPermissionEntity.getCodeSys() + ":" + webPermissionEntity.getCodeModule() + ":"
									+ webPermissionEntity.getCode();
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

	private boolean bindUserAndRoleByUserIdAndRoleCodeDelBefore(Long userId, List<String> roleCodeList) {
		QueryWrapper<WebUserRoleEntity> webUserRoleEntityQueryWrapper = new QueryWrapper<>();
		webUserRoleEntityQueryWrapper.lambda().eq(WebUserRoleEntity::getUserId, userId);
		webUserRoleEntityService.remove(webUserRoleEntityQueryWrapper);
		if (ObjectUtil.isNotEmpty(roleCodeList)) {
			for (String roleCode : roleCodeList) {
				QueryWrapper<WebRoleEntity> webRoleEntityQueryWrapper = new QueryWrapper<>();
				webRoleEntityQueryWrapper.lambda().eq(WebRoleEntity::getCode, roleCode);
				WebRoleEntity webRoleEntity = webRoleEntityService.getOne(webRoleEntityQueryWrapper);
				if (ObjectUtil.isNotNull(webRoleEntity)) {
					WebUserRoleEntity webUserRoleEntity = new WebUserRoleEntity();
					webUserRoleEntity.setUserId(userId);
					webUserRoleEntity.setRoleId(webRoleEntity.getTableId());
					webUserRoleEntityService.save(webUserRoleEntity);
				}
			}
		}
		return true;
	}
}
