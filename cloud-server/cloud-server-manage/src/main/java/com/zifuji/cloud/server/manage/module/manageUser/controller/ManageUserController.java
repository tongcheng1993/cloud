package com.zifuji.cloud.server.manage.module.manageUser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.module.feign.bean.Inner;
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

	public Result<Boolean> addManageUserManageRoleRelationAndLogout(
			@RequestBody @Valid AddManageUserManageRoleRelationAndLogoutMo addManageUserManageRoleRelationAndLogoutMo) {
		return Result.setObj(true);
	}

	public Result<Boolean> manageUserLogout(@RequestBody @Valid ManageUserLogoutMo manageUserLogoutMo) {
		return Result.setObj(manageUserService.manageUserLogout(manageUserLogoutMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定用户和角色")
	@PostMapping(value = "/bindUserAndRoleDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:bindUserAndRoleDelBefore')")
	public Result<Boolean> bindUserAndRoleDelBefore(
			@RequestBody @Valid BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo) {
		return Result.setObj(manageUserService.bindUserAndRoleDelBefore(bindUserAndRoleDelBeforeMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定角色和接口权限")
	@PostMapping(value = "/bindRoleAndPermissionDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:bindRoleAndPermissionDelBefore')")
	public Result<Boolean> bindRoleAndPermissionDelBefore(
			@RequestBody @Valid BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo) {
		return Result.setObj(manageUserService.bindRoleAndPermissionDelBefore(bindRoleAndPermissionDelBeforeMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "绑定角色和页面路由")
	@PostMapping(value = "/bindRoleAndMenuDelBefore")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:bindRoleAndMenuDelBefore')")
	public Result<Boolean> bindRoleAndMenuDelBefore(
			@RequestBody @Valid BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo) {
		return Result.setObj(manageUserService.bindRoleAndMenuDelBefore(bindRoleAndMenuDelBeforeMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "重置密码")
	@PostMapping(value = "/resetPassWord")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:resetPassWord')")
	public Result<Boolean> resetPassWord(@RequestBody @Valid ResetPassWordMo resetPassWordMo) {
		return Result.setObj(manageUserService.resetPassWord(resetPassWordMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "获取路由")
	@PostMapping(value = "/getMenu")
	public Result<List<ManageMenuVo>> getMenu() {
		return Result.setObj(manageUserService.getMenu());
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "登出")
	@PostMapping(value = "/logout")
	public Result<Boolean> logout() {
		return Result.setObj(manageUserService.logout());
	}

	@ApiOperation(value = "账号密码登录")
	@PostMapping(value = "/login")
	public Result<String> login(@RequestBody @Valid LoginMo loginMo) {
		return Result.setObj(manageUserService.login(loginMo));
	}

	@ApiOperation(value = "根据验证码ID获取图片")
	@PostMapping(value = "/drawCaptcha")
	public Result<DrawCaptchaVo> drawCaptcha() {
		return Result.setObj(manageUserService.drawCaptcha());
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "保存内网账号")
	@PostMapping(value = "/addManageUser")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:addManageUser')")
	public Result<ManageUserVo> addManageUser(@RequestBody @Valid AddManageUserMo addManageUserMo) {
		return Result.setObj(manageUserService.addManageUser(addManageUserMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除内网账号")
	@PostMapping(value = "/delManageUser")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:delManageUser')")
	public Result<Boolean> delManageUser(@RequestBody @Valid UpdateManageUserMo updateManageUserMo) {
		return Result.setObj(manageUserService.delManageUser(updateManageUserMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改内网账号")
	@PostMapping(value = "/updateManageUser")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:updateManageUser')")
	public Result<ManageUserVo> updateManageUser(@RequestBody @Valid UpdateManageUserMo updateManageUserMo) {
		return Result.setObj(manageUserService.updateManageUser(updateManageUserMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网账号")
	@PostMapping(value = "/queryPageManageUser")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryPageManageUser')")
	public Result<IPage<ManageUserVo>> queryPageManageUser(
			@RequestBody @Valid QueryPageManageUserQo queryPageManageUserQo) {
		return Result.setObj(manageUserService.queryPageManageUser(queryPageManageUserQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网账号")
	@PostMapping(value = "/getManageUserById")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:getManageUserById')")
	public Result<ManageUserVo> getManageUserById(@RequestBody @Valid UpdateManageUserMo updateManageUserMo) {
		return Result.setObj(manageUserService.getManageUserById(updateManageUserMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "新增管理端角色")
	@PostMapping(value = "/addManageRole")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:addManageRole')")
	public Result<ManageRoleVo> addManageRole(@RequestBody @Valid AddManageRoleMo addManageRoleMo) {
		return Result.setObj(manageUserService.addManageRole(addManageRoleMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除管理端角色")
	@PostMapping(value = "/delManageRole")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:delManageRole')")
	public Result<Boolean> delManageRole(@RequestBody @Valid UpdateManageRoleMo updateManageRoleMo) {
		return Result.setObj(manageUserService.delManageRole(updateManageRoleMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改管理端角色")
	@PostMapping(value = "/updateManageRole")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:updateManageRole')")
	public Result<ManageRoleVo> updateManageRole(@RequestBody @Valid UpdateManageRoleMo updateManageRoleMo) {
		return Result.setObj(manageUserService.updateManageRole(updateManageRoleMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网角色")
	@PostMapping(value = "/queryListManageRole")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryListManageRole')")
	public Result<List<ManageRoleVo>> queryListManageRole(
			@RequestBody @Valid QueryPageManageRoleQo queryPageManageRoleQo) {
		return Result.setObj(manageUserService.queryListManageRole(queryPageManageRoleQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页查询内网角色")
	@PostMapping(value = "/queryPageManageRole")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryPageManageRole')")
	public Result<IPage<ManageRoleVo>> queryPageManageRole(
			@RequestBody @Valid QueryPageManageRoleQo queryPageManageRoleQo) {
		return Result.setObj(manageUserService.queryPageManageRole(queryPageManageRoleQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询内网角色")
	@PostMapping(value = "/getManageRoleById")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:getManageRoleById')")
	public Result<ManageRoleVo> getManageRoleById(@RequestBody @Valid UpdateManageRoleMo updateManageRoleMo) {
		return Result.setObj(manageUserService.getManageRoleById(updateManageRoleMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "增加管理端接口权限标识接口")
	@PostMapping(value = "/addManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:addManagePermission')")
	public Result<ManagePermissionVo> addManagePermission(
			@RequestBody @Valid AddManagePermissionMo addManagePermissionMo) {
		return Result.setObj(manageUserService.addManagePermission(addManagePermissionMo));
	}

	@PostMapping(value = "/addManagePermissionInner")
	@Inner
	public Result<ManagePermissionVo> addManagePermissionInner(
			@RequestBody @Valid AddManagePermissionMo addManagePermissionMo) {
		return Result.setObj(manageUserService.addManagePermission(addManagePermissionMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "删除管理端权限")
	@PostMapping(value = "/delManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:delManagePermission')")
	public Result<Boolean> delManagePermission(@RequestBody @Valid UpdateManagePermissionMo updateManagePermissionMo) {
		return Result.setObj(manageUserService.delManagePermission(updateManagePermissionMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "修改管理端接口权限标识接口")
	@PostMapping(value = "/updateManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:updateManagePermission')")
	public Result<ManagePermissionVo> updateManagePermission(
			@RequestBody @Valid UpdateManagePermissionMo updateManagePermissionMo) {
		return Result.setObj(manageUserService.updateManagePermission(updateManagePermissionMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端接口权限")
	@PostMapping(value = "/queryListManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryListManagePermission')")
	public Result<List<ManagePermissionVo>> queryListManagePermission(
			@RequestBody @Valid QueryPageManagePermissionQo queryPageManagePermissionQo) {
		return Result.setObj(manageUserService.queryListManagePermission(queryPageManagePermissionQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "分页查询管理端接口权限")
	@PostMapping(value = "/queryPageManagePermission")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryPageManagePermission')")
	public Result<IPage<ManagePermissionVo>> queryPageManagePermission(
			@RequestBody @Valid QueryPageManagePermissionQo queryPageManagePermissionQo) {
		return Result.setObj(manageUserService.queryPageManagePermission(queryPageManagePermissionQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/getManagePermissionById")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:getManagePermissionById')")
	public Result<ManagePermissionVo> getManagePermissionById(
			@RequestBody @Valid UpdateManagePermissionMo updateManagePermissionMo) {
		return Result.setObj(manageUserService.getManagePermissionById(updateManagePermissionMo.getTableId()));
	};

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/addManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:addManageMenu')")
	public Result<ManageMenuVo> addManageMenu(@RequestBody @Valid AddManageMenuMo addManageMenuMo) {
		return Result.setObj(manageUserService.addManageMenu(addManageMenuMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/delManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:delManageMenu')")
	public Result<Boolean> delManageMenu(@RequestBody @Valid UpdateManageMenuMo updateManageMenuMo) {
		return Result.setObj(manageUserService.delManageMenu(updateManageMenuMo.getTableId()));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "管理端")
	@PostMapping(value = "/updateManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:updateManageMenu')")
	public Result<ManageMenuVo> updateManageMenu(@RequestBody @Valid UpdateManageMenuMo updateManageMenuMo) {
		return Result.setObj(manageUserService.updateManageMenu(updateManageMenuMo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/queryListManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryListManageMenu')")
	public Result<List<ManageMenuVo>> queryListManageMenu(
			@RequestBody @Valid QueryPageManageMenuQo queryPageManageMenuQo) {
		return Result.setObj(manageUserService.queryListManageMenu(queryPageManageMenuQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/queryPageManageMenu")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:queryPageManageMenu')")
	public Result<IPage<ManageMenuVo>> queryPageManageMenu(
			@RequestBody @Valid QueryPageManageMenuQo queryPageManageMenuQo) {
		return Result.setObj(manageUserService.queryPageManageMenu(queryPageManageMenuQo));
	}

	@ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
	@ApiOperation(value = "查询管理端页面路由")
	@PostMapping(value = "/getManageMenuById")
	@PreAuthorize(value = "hasAnyAuthority('manage:manageUser:getManageMenuById')")
	public Result<ManageMenuVo> getManageMenuById(@RequestBody @Valid UpdateManageMenuMo updateManageMenuMo) {
		return Result.setObj(manageUserService.getManageMenuById(updateManageMenuMo.getTableId()));
	}

}
