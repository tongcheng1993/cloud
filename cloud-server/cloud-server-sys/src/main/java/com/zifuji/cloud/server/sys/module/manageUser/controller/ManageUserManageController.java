package com.zifuji.cloud.server.sys.module.manageUser.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageMenuPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManagePermissionPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageRolePageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageUserPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageMenuControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManagePermissionControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageRoleControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageUserControllerVo;
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
@Api(value = "manageUser/manage")
@RestController
@RequestMapping(value = "/manageUser/manage")
@AllArgsConstructor
public class ManageUserManageController {

    private ManageUserService manageUserService;

    @ApiOperation(value = "查询内网账号")
    @PostMapping(value = "/queryPageUser")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryPageUser')")
    public Result<IPage<ManageUserControllerVo>> queryPageUser(@RequestBody @Valid ManageUserPageQo manageUserPageQo) {

        IPage<ManageUserControllerVo> result = manageUserService.queryPageUser(manageUserPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "查询内网账号")
    @PostMapping(value = "/queryListUser")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListUser')")
    public Result<List<ManageUserControllerVo>> queryListUser(@RequestBody @Valid ManageUserPageQo manageUserPageQo) {

        List<ManageUserControllerVo> result = manageUserService.queryListUser(manageUserPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存内网账号")
    @PostMapping(value = "/saveUser")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveUser')")
    public Result<ManageUserControllerVo> saveUser(@RequestBody @Valid ManageUserControllerMo manageUserMo) {

        ManageUserControllerVo result = manageUserService.saveUser(manageUserMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "管理员重置内网密码")
    @PostMapping(value = "/resetPassWord")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:resetPassWord')")
    public Result<ManageUserControllerVo> resetPassWord(@RequestBody @Valid ResetPassWordControllerMo resetPassWordMo) {

        ManageUserControllerVo result = manageUserService.resetPassWord(resetPassWordMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/queryPageRole")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryPageRole')")
    public Result<IPage<ManageRoleControllerVo>> queryPageRole(@RequestBody @Valid ManageRolePageQo manageRolePageQo) {

        IPage<ManageRoleControllerVo> result = manageUserService.queryPageRole(manageRolePageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/queryListRole")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListRole')")
    public Result<List<ManageRoleControllerVo>> queryListRole(@RequestBody @Valid ManageRolePageQo manageRolePageQo) {

        List<ManageRoleControllerVo> result = manageUserService.queryListRole(manageRolePageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "查询内网权限")
    @PostMapping(value = "/queryListPermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListPermission')")
    public Result<List<ManagePermissionControllerVo>> queryListPermission(@RequestBody @Valid ManagePermissionPageQo managePermissionPageQo) {

        List<ManagePermissionControllerVo> result = manageUserService.queryListPermission(managePermissionPageQo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "查询内网权限")
    @PostMapping(value = "/queryPagePermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryPagePermission')")
    public Result<IPage<ManagePermissionControllerVo>> queryPagePermission(@RequestBody @Valid ManagePermissionPageQo managePermissionPageQo) {

        IPage<ManagePermissionControllerVo> result = manageUserService.queryPagePermission(managePermissionPageQo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "新增内网权限")
    @PostMapping(value = "/savePermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:savePermission')")
    public Result<String> savePermission(@RequestBody @Valid ManagePermissionControllerMo managePermissionMo) {

        String result = manageUserService.savePermission(managePermissionMo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "查询内网路由")
    @PostMapping(value = "/queryListMenu")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListMenu')")
    public Result<List<ManageMenuControllerVo>> queryListMenu(@RequestBody @Valid ManageMenuPageQo manageMenuPageQo) {

        List<ManageMenuControllerVo> result = manageUserService.queryListMenu(manageMenuPageQo);

        return Result.setObj(result);
    }


    @ApiOperation(value = "保存内网路由")
    @PostMapping(value = "/saveMenu")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveMenu')")
    public Result<String> saveMenu(@RequestBody @Valid ManageMenuControllerMo manageMenuMo) {

        String result = manageUserService.saveMenu(manageMenuMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存用户角色关系")
    @PostMapping(value = "/saveUserRoleRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveUserRoleRelation')")
    public Result<String> saveUserRoleRelation(@RequestBody @Valid ManageUserRoleRelationControllerMo manageUserRoleRelationMo) {

        String result = manageUserService.saveUserRoleRelation(manageUserRoleRelationMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存角色权限关系")
    @PostMapping(value = "/saveRolePermissionRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveRolePermissionRelation')")
    public Result<String> saveRolePermissionRelation(@RequestBody @Valid ManageRolePermissionRelationControllerMo manageRolePermissionRelationMo) {

        String result = manageUserService.saveRolePermissionRelation(manageRolePermissionRelationMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "保存角色路由关系")
    @PostMapping(value = "/saveRoleMenuRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveRoleMenuRelation')")
    public Result<String> saveRoleMenuRelation(@RequestBody @Valid ManageRoleMenuRelationControllerMo manageRoleMenuRelationMo) {

        String result = manageUserService.saveRoleMenuRelation(manageRoleMenuRelationMo);

        return Result.setObj(result);
    }


}
