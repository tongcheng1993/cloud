package com.zifuji.cloud.server.sys.module.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;

import com.zifuji.cloud.server.sys.module.user.mo.*;

import com.zifuji.cloud.server.sys.module.user.qo.WebMenuPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebRolePageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebUserPageQo;
import com.zifuji.cloud.server.sys.module.user.service.UserService;
import com.zifuji.cloud.server.sys.module.user.vo.WebMenuControllerVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebPermissionVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebRoleControllerVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebUserControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "用户/管理")
@RestController
@RequestMapping(value = "/user/manage")
@AllArgsConstructor
public class UserManageController {

    private UserService userService;

    @ApiOperation(value = "分页查询外网用户")
    @PostMapping(value = "/queryPageUser")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPageUser')")
    public Result<IPage<WebUserControllerVo>> queryPageUser(@RequestBody @Valid WebUserPageQo webUserPageQo) {

        IPage<WebUserControllerVo> result = userService.queryPageUser(webUserPageQo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "分页查询外网角色")
    @PostMapping(value = "/queryPageRole")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPageRole')")
    public Result<IPage<WebRoleControllerVo>> queryPageRole(@RequestBody @Valid WebRolePageQo webRolePageQo) {

        IPage<WebRoleControllerVo> result = userService.queryPageRole(webRolePageQo);

        return Result.setObj(userService.queryPageRole(webRolePageQo));
    }

    @ApiOperation(value = "保存外网角色")
    @PostMapping(value = "/saveRole")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRole')")
    public Result<String> saveRole(@RequestBody @Valid SaveWebRoleControllerMo saveWebRoleMo) {

        String result = userService.saveRole(saveWebRoleMo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "保存外网路由")
    @PostMapping(value = "/saveMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:saveMenu')")
    public Result<String> saveMenu(@RequestBody @Valid SaveWebMenuControllerMo saveWebMenuMo) {

        String result = userService.saveMenu(saveWebMenuMo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "查询外网路由")
    @PostMapping(value = "/queryListMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:queryListMenu')")
    public Result<List<WebMenuControllerVo>> queryListMenu(@RequestBody @Valid WebMenuPageQo webMenuPageQo) {

        List<WebMenuControllerVo> result = userService.queryListMenu(webMenuPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存外网角色和路由的关系")
    @PostMapping(value = "/saveRoleAndMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRoleAndMenu')")
    public Result<String> saveRoleAndMenu(@RequestBody @Valid SaveRoleAndMenuControllerMo saveRoleAndMenuMo) {

        String result = userService.saveRoleAndMenu(saveRoleAndMenuMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存外网权限")
    @PostMapping(value = "/savePermission")
    @PreAuthorize(value = "hasAuthority('sys:user:savePermission')")
    public Result<String> savePermission(@RequestBody @Valid SaveWebPermissionControllerMo saveWebPermissionMo) {

        String result = userService.savePermission(saveWebPermissionMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "分页查询外网权限")
    @PostMapping(value = "/queryPagePermission")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPagePermission')")
    public Result<IPage<WebPermissionVo>> queryPagePermission(@RequestBody @Valid WebPermissionPageQo webPermissionPageQo) {

        IPage<WebPermissionVo> result = userService.queryPagePermission(webPermissionPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "分页查询外网权限")
    @PostMapping(value = "/queryListPermission")
    @PreAuthorize(value = "hasAuthority('sys:user:queryListPermission')")
    public Result<List<WebPermissionVo>> queryListPermission(@RequestBody @Valid WebPermissionPageQo webPermissionPageQo) {

        List<WebPermissionVo> result = userService.queryListPermission(webPermissionPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存外网角色和路由的关系")
    @PostMapping(value = "/saveRoleAndPermission")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRoleAndPermission')")
    public Result<String> saveRoleAndPermission(@RequestBody @Valid SaveRoleAndPermissionControllerMo saveRoleAndPermissionMo) {

        String result = userService.saveRoleAndPermission(saveRoleAndPermissionMo);

        return Result.setObj(result);

    }


}
