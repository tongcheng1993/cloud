package com.zifuji.cloud.server.manage.module.manageUser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.manage.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.manage.module.manageUser.controller.vo.*;
import com.zifuji.cloud.server.manage.module.manageUser.service.ManageUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "管理端用户控制器")
@RestController
@RequestMapping(value = "/manageUser")
@AllArgsConstructor
public class ManageUserController {
	// 管理端用户服务控制接口
	private ManageUserService manageUserService;

	@ApiOperation(value = "根据验证码ID获取图片")
	@PostMapping(value = "/drawCaptcha")
	public Result<DrawCaptchaVo> drawCaptcha() {
		return Result.setObj(manageUserService.drawCaptcha());
	}

	@ApiOperation(value = "账号密码登录")
	@PostMapping(value = "/login")
	public Result<String> login(@RequestBody @Valid LoginMo loginMo) {
		return Result.setObj(manageUserService.login(loginMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "登出")
	@PostMapping(value = "/logout")
	public Result<Boolean> logout() {
		return Result.setObj(manageUserService.logout());
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "获取路由")
	@PostMapping(value = "/getMenu")
	public Result<List<ManageMenuVo>> getMenu() {
		return Result.setObj(manageUserService.getMenu());
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "保存内网账号")
	@PostMapping(value = "/addManageUser")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManageUser')")
	public Result<ManageUserVo> addManageUser(@RequestBody @Valid AddManageUserMo addManageUserMo) {
		return Result.setObj(manageUserService.addManageUser(addManageUserMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除内网账号")
	@PostMapping(value = "/delManageUser")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:delManageUser')")
	public Result<Boolean> delManageUser(@RequestParam Long id) {
		return Result.setObj(manageUserService.delManageUser(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改内网账号")
	@PostMapping(value = "/updateManageUser")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:resetManageUser')")
	public Result<ManageUserVo> updateManageUser(@RequestBody @Valid UpdateManageUserMo updateManageUserMo) {
		return Result.setObj(manageUserService.updateManageUser(updateManageUserMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网账号")
	@PostMapping(value = "/queryPageManageUser")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManageUser')")
	public Result<IPage<ManageUserVo>> queryPageManageUser(
			@RequestBody @Valid QueryPageManageUserQo queryPageManageUserQo) {
		return Result.setObj(manageUserService.queryPageManageUser(queryPageManageUserQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网账号")
	@GetMapping(value = "/getManageUserById")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageUserById')")
	public Result<ManageUserVo> getManageUserById(@RequestParam Long id) {
		return Result.setObj(manageUserService.getManageUserById(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "新增管理端角色")
	@PostMapping(value = "/addManageRole")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManageRole')")
	public Result<ManageRoleVo> addManageRole(@RequestBody @Valid AddManageRoleMo addManageRoleMo) {
		return Result.setObj(manageUserService.addManageRole(addManageRoleMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除管理端角色")
	@PostMapping(value = "/delManageRole")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:delManageRole')")
	public Result<Boolean> delManageRole(@RequestParam Long id) {
		return Result.setObj(manageUserService.delManageRole(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改管理端角色")
	@PostMapping(value = "/updateManageRole")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:resetManageRole')")
	public Result<ManageRoleVo> updateManageRole(@RequestBody @Valid UpdateManageRoleMo updateManageRoleMo) {
		return Result.setObj(manageUserService.updateManageRole(updateManageRoleMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网角色")
	@PostMapping(value = "/queryListManageRole")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManageRole')")
	public Result<List<ManageRoleVo>> queryListManageRole(
			@RequestBody @Valid QueryPageManageRoleQo queryPageManageRoleQo) {
		return Result.setObj(manageUserService.queryListManageRole(queryPageManageRoleQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页查询内网角色")
	@PostMapping(value = "/queryPageManageRole")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManageRole')")
	public Result<IPage<ManageRoleVo>> queryPageManageRole(
			@RequestBody @Valid QueryPageManageRoleQo queryPageManageRoleQo) {
		return Result.setObj(manageUserService.queryPageManageRole(queryPageManageRoleQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网角色")
	@PostMapping(value = "/getManageRoleById")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageRoleById')")
	public Result<ManageRoleVo> getManageRoleById(@RequestParam Long id) {
		return Result.setObj(manageUserService.getManageRoleById(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "增加管理端接口权限标识接口")
	@PostMapping(value = "/addManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManagePermission')")
	public Result<ManagePermissionVo> addManagePermission(
			@RequestBody @Valid AddManagePermissionMo addManagePermissionMo) {
		return Result.setObj(manageUserService.addManagePermission(addManagePermissionMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除管理端权限")
	@PostMapping(value = "/delManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:delManagePermission')")
	public Result<Boolean> delManagePermission(@RequestParam Long id) {
		return Result.setObj(manageUserService.delManagePermission(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改管理端接口权限标识接口")
	@PostMapping(value = "/updateManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:updateManagePermission')")
	public Result<ManagePermissionVo> updateManagePermission(
			@RequestBody @Valid UpdateManagePermissionMo updateManagePermissionMo) {
		return Result.setObj(manageUserService.updateManagePermission(updateManagePermissionMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端接口权限")
	@PostMapping(value = "/queryListManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManagePermission')")
	public Result<List<ManagePermissionVo>> queryListManagePermission(
			@RequestBody @Valid QueryPageManagePermissionQo queryPageManagePermissionQo) {
		return Result.setObj(manageUserService.queryListManagePermission(queryPageManagePermissionQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页查询管理端接口权限")
	@PostMapping(value = "/queryPageManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManagePermission')")
	public Result<IPage<ManagePermissionVo>> queryPageManagePermission(
			@RequestBody @Valid QueryPageManagePermissionQo queryPageManagePermissionQo) {
		return Result.setObj(manageUserService.queryPageManagePermission(queryPageManagePermissionQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/getManagePermissionById")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManagePermissionById')")
	public Result<ManagePermissionVo> getManagePermissionById(@RequestParam Long id) {
		return Result.setObj(manageUserService.getManagePermissionById(id));
	};

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/addManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManageMenu')")
	public Result<ManageMenuVo> addManageMenu(@RequestBody @Valid AddManageMenuMo addManageMenuMo) {
		return Result.setObj(manageUserService.addManageMenu(addManageMenuMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/delManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:delManageMenu')")
	public Result<Boolean> delManageMenu(@RequestParam Long id) {
		return Result.setObj(manageUserService.delManageMenu(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/updateManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:updateManageMenu')")
	public Result<ManageMenuVo> updateManageMenu(@RequestBody @Valid UpdateManageMenuMo updateManageMenuMo) {
		return Result.setObj(manageUserService.updateManageMenu(updateManageMenuMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/queryListManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManageMenu')")
	public Result<List<ManageMenuVo>> queryListManageMenu(
			@RequestBody @Valid QueryPageManageMenuQo queryPageManageMenuQo) {
		return Result.setObj(manageUserService.queryListManageMenu(queryPageManageMenuQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/queryPageManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManageMenu')")
	public Result<IPage<ManageMenuVo>> queryPageManageMenu(
			@RequestBody @Valid QueryPageManageMenuQo queryPageManageMenuQo) {
		return Result.setObj(manageUserService.queryPageManageMenu(queryPageManageMenuQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/getManageMenuById")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageMenuById')")
	public Result<ManageMenuVo> getManageMenuById(@RequestParam Long id) {
		return Result.setObj(manageUserService.getManageMenuById(id));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定角色和页面路由")
	@PostMapping(value = "/bindRoleAndMenuDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindRoleAndMenuDelBefore')")
	public Result<Boolean> bindRoleAndMenuDelBefore(
			@RequestBody @Valid BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo) {
		return Result.setObj(manageUserService.bindRoleAndMenuDelBefore(bindRoleAndMenuDelBeforeMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定角色和接口权限")
	@PostMapping(value = "/bindRoleAndPermissionDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindRoleAndPermissionDelBefore')")
	public Result<Boolean> bindRoleAndPermissionDelBefore(
			@RequestBody @Valid BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo) {
		return Result.setObj(manageUserService.bindRoleAndPermissionDelBefore(bindRoleAndPermissionDelBeforeMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定用户和角色")
	@PostMapping(value = "/bindUserAndRoleDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindUserAndRoleDelBefore')")
	public Result<Boolean> bindUserAndRoleDelBefore(
			@RequestBody @Valid BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo) {
		return Result.setObj(manageUserService.bindUserAndRoleDelBefore(bindUserAndRoleDelBeforeMo));
	}

}
