package com.zifuji.cloud.server.business.module.webUser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.LoginMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.RegisterMo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebMenuQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebRoleQo;
import com.zifuji.cloud.server.business.module.webUser.controller.qo.QueryWebUserQo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.*;
import com.zifuji.cloud.server.business.module.webUser.service.WebUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(tags = "注册用户控制器")
@RestController
@RequestMapping(value = "/webUser")
@AllArgsConstructor
public class WebUserController {

	private WebUserService webUserService;

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "根据验证码ID获取图片")
	@PostMapping(value = "/drawCaptcha")
	public Result<DrawCaptchaVo> drawCaptcha() {
		DrawCaptchaVo vo = webUserService.drawCaptcha();
		return Result.setObj(vo);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "登录")
	@PostMapping(value = "/login")
	public Result<String> login(@RequestBody @Valid LoginMo loginMo) {
		String token = webUserService.login(loginMo);
		return Result.setObj(token);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = false, paramType = "header")
	@ApiOperation(value = "账号密码注册")
	@PostMapping(value = "/register")
	public Result<String> register(@RequestBody @Valid RegisterMo registerMo) {
		String token = webUserService.register(registerMo);
		return Result.setObj(token);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "获取自身信息")
	@PostMapping(value = "/getMyselfInfo")
	public Result<WebUserVo> getMyselfInfo() {
		WebUserVo vo = webUserService.getMyselfInfo();
		return Result.setObj(vo);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "退出")
	@PostMapping(value = "/logout")
	public Result<Boolean> logout() {
		Boolean flag = webUserService.logout();
		return Result.setObj(flag);
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页申报端用户")
	@PostMapping(value = "/queryPageWebUser")
	public Result<IPage<WebUserVo>> queryPageWebUser(@RequestBody @Valid QueryWebUserQo queryWebUserQo) {
		return Result.setObj(webUserService.queryPageWebUser(queryWebUserQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页申报端角色")
	@PostMapping(value = "/queryPageWebRole")
	@PreAuthorize(value = "hasAnyAuthority('business:webUser:queryPageWebRole')")
	public Result<IPage<WebRoleVo>> queryPageWebRole(@RequestBody @Valid QueryWebRoleQo queryWebRoleQo) {
		return Result.setObj(webUserService.queryPageWebRole(queryWebRoleQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "全部申报端路由")
	@PostMapping(value = "/queryListWebMenu")
	@PreAuthorize(value = "hasAnyAuthority('business:webUser:queryListWebMenu')")
	public Result<List<WebMenuVo>> queryListWebMenu(@RequestBody @Valid QueryWebMenuQo queryWebMenuQo) {
		return Result.setObj(webUserService.queryListWebMenu(queryWebMenuQo));
	}

}
