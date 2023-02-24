package com.zifuji.cloud.server.sys.module.manageUser.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.mo.*;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageMenuPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManagePermissionPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageRolePageQo;
import com.zifuji.cloud.server.sys.module.manageUser.qo.ManageUserPageQo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageMenuVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManagePermissionVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageRoleVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageUserVo;
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
    public Result<IPage<ManageUserVo>> queryPageUser(@RequestBody @Valid ManageUserPageQo manageUserPageQo) {
        log.info(JSONObject.toJSONString(manageUserPageQo));
        IPage<ManageUserVo> result = manageUserService.queryPageUser(manageUserPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<ManageUserVo>>().setObj(result);
    }

    @ApiOperation(value = "查询内网账号")
    @PostMapping(value = "/queryListUser")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListUser')")
    public Result<List<ManageUserVo>> queryListUser(@RequestBody @Valid ManageUserPageQo manageUserPageQo) {
        log.info(JSONObject.toJSONString(manageUserPageQo));
        List<ManageUserVo> result = manageUserService.queryListUser(manageUserPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<ManageUserVo>>().setObj(result);
    }

    @ApiOperation(value = "保存内网账号")
    @PostMapping(value = "/saveUser")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveUser')")
    public Result<ManageUserVo> saveUser(@RequestBody @Valid ManageUserMo manageUserMo) {
        log.info(JSONObject.toJSONString(manageUserMo));
        ManageUserVo result = manageUserService.saveUser(manageUserMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<ManageUserVo>().setObj(result);
    }

    @ApiOperation(value = "管理员重置内网密码")
    @PostMapping(value = "/resetPassWord")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:resetPassWord')")
    public Result<ManageUserVo> resetPassWord(@RequestBody @Valid ResetPassWordMo resetPassWordMo) {
        log.info(JSONObject.toJSONString(resetPassWordMo));
        ManageUserVo result = manageUserService.resetPassWord(resetPassWordMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<ManageUserVo>().setObj(result);
    }

    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/queryPageRole")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryPageRole')")
    public Result<IPage<ManageRoleVo>> queryPageRole(@RequestBody @Valid ManageRolePageQo manageRolePageQo) {
        log.info(JSONObject.toJSONString(manageRolePageQo));
        IPage<ManageRoleVo> result = manageUserService.queryPageRole(manageRolePageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<ManageRoleVo>>().setObj(result);
    }

    @ApiOperation(value = "查询内网角色")
    @PostMapping(value = "/queryListRole")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListRole')")
    public Result<List<ManageRoleVo>> queryListRole(@RequestBody @Valid ManageRolePageQo manageRolePageQo) {
        log.info(JSONObject.toJSONString(manageRolePageQo));
        List<ManageRoleVo> result = manageUserService.queryListRole(manageRolePageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<ManageRoleVo>>().setObj(result);
    }

    @ApiOperation(value = "查询内网权限")
    @PostMapping(value = "/queryListPermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListPermission')")
    public Result<List<ManagePermissionVo>> queryListPermission(@RequestBody @Valid ManagePermissionPageQo managePermissionPageQo) {
        log.info(JSONObject.toJSONString(managePermissionPageQo));
        List<ManagePermissionVo> result = manageUserService.queryListPermission(managePermissionPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<ManagePermissionVo>>().setObj(result);
    }


    @ApiOperation(value = "查询内网权限")
    @PostMapping(value = "/queryPagePermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryPagePermission')")
    public Result<IPage<ManagePermissionVo>> queryPagePermission(@RequestBody @Valid ManagePermissionPageQo managePermissionPageQo) {
        log.info(JSONObject.toJSONString(managePermissionPageQo));
        IPage<ManagePermissionVo> result = manageUserService.queryPagePermission(managePermissionPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<ManagePermissionVo>>().setObj(result);
    }

    @ApiOperation(value = "新增内网权限")
    @PostMapping(value = "/savePermission")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:savePermission')")
    public Result<String> savePermission(@RequestBody @Valid ManagePermissionMo managePermissionMo) {
        log.info(JSONObject.toJSONString(managePermissionMo));
        String result = manageUserService.savePermission(managePermissionMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "查询内网路由")
    @PostMapping(value = "/queryListMenu")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:queryListMenu')")
    public Result<List<ManageMenuVo>> queryListMenu(@RequestBody @Valid ManageMenuPageQo manageMenuPageQo) {
        log.info(JSONObject.toJSONString(manageMenuPageQo));
        List<ManageMenuVo> result = manageUserService.queryListMenu(manageMenuPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<ManageMenuVo>>().setObj(result);
    }


    @ApiOperation(value = "保存内网路由")
    @PostMapping(value = "/saveMenu")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveMenu')")
    public Result<String> saveMenu(@RequestBody @Valid ManageMenuMo manageMenuMo) {
        log.info(JSONObject.toJSONString(manageMenuMo));
        String result = manageUserService.saveMenu(manageMenuMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "保存用户角色关系")
    @PostMapping(value = "/saveUserRoleRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveUserRoleRelation')")
    public Result<String> saveUserRoleRelation(@RequestBody @Valid ManageUserRoleRelationMo manageUserRoleRelationMo) {
        log.info(JSONObject.toJSONString(manageUserRoleRelationMo));
        String result = manageUserService.saveUserRoleRelation(manageUserRoleRelationMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "保存角色权限关系")
    @PostMapping(value = "/saveRolePermissionRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveRolePermissionRelation')")
    public Result<String> saveRolePermissionRelation(@RequestBody @Valid ManageRolePermissionRelationMo manageRolePermissionRelationMo) {
        log.info(JSONObject.toJSONString(manageRolePermissionRelationMo));
        String result = manageUserService.saveRolePermissionRelation(manageRolePermissionRelationMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "保存角色路由关系")
    @PostMapping(value = "/saveRoleMenuRelation")
    @PreAuthorize(value = "hasAuthority('sys:manageUser:saveRoleMenuRelation')")
    public Result<String> saveRoleMenuRelation(@RequestBody @Valid ManageRoleMenuRelationMo manageRoleMenuRelationMo) {
        log.info(JSONObject.toJSONString(manageRoleMenuRelationMo));
        String result = manageUserService.saveRoleMenuRelation(manageRoleMenuRelationMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


}
