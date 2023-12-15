package com.zifuji.cloud.server.sys.module.manageUser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.controller.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.controller.qo.*;
import com.zifuji.cloud.server.sys.module.manageUser.controller.vo.*;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
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
        return Result.setObj(true);
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "获取路由")
    @PostMapping(value = "/getMenu")
    public Result<List<ManageMenuVo>> getMenu() {
        return Result.setObj(manageUserService.getMenu());
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网账号")
    @PostMapping(value = "/queryPageManageUser")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManageUser')")
    public Result<IPage<ManageUserVo>> queryPageManageUser(@RequestBody @Valid QueryPageManageUserQo queryPageManageUserQo) {
        return Result.setObj(manageUserService.queryPageManageUser(queryPageManageUserQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网账号")
    @GetMapping(value = "/getManageUserById")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageUserById')")
    public Result<ManageUserVo> getManageUserById(@RequestParam String id) {
        return Result.setObj(manageUserService.getManageUserById(id));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网账号")
    @GetMapping(value = "/getManageUserByUserName")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageUserByUserName')")
    public Result<ManageUserVo> getManageUserByUserName(@RequestParam String userName) {
        return Result.setObj(manageUserService.getManageUserByUserName(userName));
    }


    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "保存内网账号")
    @PostMapping(value = "/addManageUser")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManageUser')")
    public Result<Boolean> addManageUser(@RequestBody @Valid AddManageUserMo addManageUserMo) {
        return Result.setObj(manageUserService.addManageUser(addManageUserMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "修改内网账号")
    @PostMapping(value = "/resetManageUser")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:resetManageUser')")
    public Result<Boolean> resetManageUser(@RequestBody @Valid ResetManageUserMo resetManageUserMo) {
        return Result.setObj(manageUserService.resetManageUser(resetManageUserMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/queryListManageRole")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManageRole')")
    public Result<List<ManageRoleVo>> queryListManageRole(@RequestBody @Valid QueryListManageRoleQo queryListManageRoleQo) {
        return Result.setObj(manageUserService.queryListManageRole(queryListManageRoleQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "绑定用户和角色")
    @PostMapping(value = "/bindUserAndRoleDelBefore")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindUserAndRoleDelBefore')")
    public Result<Boolean> bindUserAndRoleDelBefore(@RequestBody @Valid BindUserAndRoleDelBeforeMo bindUserAndRoleDelBeforeMo) {
        return Result.setObj(manageUserService.bindUserAndRoleDelBefore(bindUserAndRoleDelBeforeMo));
    }


    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "分页查询内网角色")
    @PostMapping(value = "/queryPageManageRole")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManageRole')")
    public Result<IPage<ManageRoleVo>> queryPageManageRole(@RequestBody @Valid QueryPageManageRoleQo queryPageManageRoleQo) {
        return Result.setObj(manageUserService.queryPageManageRole(queryPageManageRoleQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/getManageRoleById")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageRoleById')")
    public Result<ManageRoleVo> getManageRoleById(@RequestParam String id) {
        return Result.setObj(manageUserService.getManageRoleById(id));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/getManageRoleByRoleCode")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:getManageRoleByRoleCode')")
    public Result<ManageRoleVo> getManageRoleByRoleCode(@RequestParam String roleCode) {
        return Result.setObj(manageUserService.getManageRoleByRoleCode(roleCode));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "新增管理端角色")
    @PostMapping(value = "/addManageRole")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManageRole')")
    public Result<Boolean> addManageRole(@RequestBody @Valid AddManageRoleMo addManageRoleMo) {
        return Result.setObj(manageUserService.addManageRole(addManageRoleMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "编辑管理端角色")
    @PostMapping(value = "/resetManageRole")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:resetManageRole')")
    public Result<Boolean> resetManageRole(@RequestBody @Valid ResetManageRoleMo resetManageRoleMo) {
        return Result.setObj(manageUserService.resetManageRole(resetManageRoleMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询管理端接口权限")
    @PostMapping(value = "/queryListManagePermission")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManagePermission')")
    public Result<List<ManagePermissionVo>> queryListManagePermission(@RequestBody @Valid QueryManagePermissionQo queryManagePermissionQo) {
        return Result.setObj(manageUserService.queryListManagePermission(queryManagePermissionQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "查询管理端页面路由")
    @PostMapping(value = "/queryListManageMenu")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryListManageMenu')")
    public Result<List<ManageMenuVo>> queryListManageMenu(@RequestBody @Valid QueryListManageMenuQo queryListManageMenuQo) {
        return Result.setObj(manageUserService.queryListManageMenu(queryListManageMenuQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "绑定角色和接口权限")
    @PostMapping(value = "/bindRoleAndPermissionDelBefore")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindRoleAndPermissionDelBefore')")
    public Result<Boolean> bindRoleAndPermissionDelBefore(@RequestBody @Valid BindRoleAndPermissionDelBeforeMo bindRoleAndPermissionDelBeforeMo) {
        return Result.setObj(manageUserService.bindRoleAndPermissionDelBefore(bindRoleAndPermissionDelBeforeMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "绑定角色和页面路由")
    @PostMapping(value = "/bindRoleAndMenuDelBefore")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:bindRoleAndMenuDelBefore')")
    public Result<Boolean> bindRoleAndMenuDelBefore(@RequestBody @Valid BindRoleAndMenuDelBeforeMo bindRoleAndMenuDelBeforeMo) {
        return Result.setObj(manageUserService.bindRoleAndMenuDelBefore(bindRoleAndMenuDelBeforeMo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "分页查询管理端接口权限")
    @PostMapping(value = "/queryPageManagePermission")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:queryPageManagePermission')")
    public Result<IPage<ManagePermissionVo>> queryPageManagePermission(@RequestBody @Valid QueryManagePermissionQo queryManagePermissionQo) {
        return Result.setObj(manageUserService.queryPageManagePermission(queryManagePermissionQo));
    }

    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "增加管理端接口权限标识接口")
    @PostMapping(value = "/addManagePermission")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:addManagePermission')")
    public Result<ManagePermissionVo> addManagePermission(@RequestBody @Valid AddManagePermissionMo addManagePermissionMo) {
        return Result.setObj(manageUserService.addManagePermission(addManagePermissionMo));
    }
    @ApiImplicitParam(name = "Tc-Token", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "修改管理端接口权限标识接口")
    @PostMapping(value = "/resetManagePermission")
    @PreAuthorize(value = "hasAnyAuthority('sys:manageUser:resetManagePermission')")
    public Result<ManagePermissionVo> resetManagePermission(@RequestBody @Valid ResetManagePermissionMo resetManagePermissionMo) {
        return null;
    }


}
