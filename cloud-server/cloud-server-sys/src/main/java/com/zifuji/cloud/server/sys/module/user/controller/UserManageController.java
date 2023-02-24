package com.zifuji.cloud.server.sys.module.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
<<<<<<< HEAD
import com.zifuji.cloud.server.sys.module.user.mo.*;
=======
import com.zifuji.cloud.server.sys.module.user.mo.SaveRoleAndMenuMo;
import com.zifuji.cloud.server.sys.module.user.mo.SaveWebMenuMo;
import com.zifuji.cloud.server.sys.module.user.mo.SaveWebPermissionMo;
import com.zifuji.cloud.server.sys.module.user.mo.SaveWebRoleMo;
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import com.zifuji.cloud.server.sys.module.user.qo.WebMenuPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebPermissionPageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebRolePageQo;
import com.zifuji.cloud.server.sys.module.user.qo.WebUserPageQo;
import com.zifuji.cloud.server.sys.module.user.service.UserService;
import com.zifuji.cloud.server.sys.module.user.vo.WebMenuVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebPermissionVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebRoleVo;
import com.zifuji.cloud.server.sys.module.user.vo.WebUserVo;
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
@Api(value = "user")
@RestController
@RequestMapping(value = "/user/manage")
@AllArgsConstructor
public class UserManageController {

    private UserService userService;

    @ApiOperation(value = "分页查询外网用户")
    @PostMapping(value = "/queryPageUser")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPageUser')")
    public Result<IPage<WebUserVo>> queryPageUser(@RequestBody @Valid WebUserPageQo webUserPageQo) {
        log.info(JSONObject.toJSONString(webUserPageQo));
        IPage<WebUserVo> result = userService.queryPageUser(webUserPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<WebUserVo>>().setObj(result);
    }


    @ApiOperation(value = "分页查询外网角色")
    @PostMapping(value = "/queryPageRole")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPageRole')")
    public Result<IPage<WebRoleVo>> queryPageRole(@RequestBody @Valid WebRolePageQo webRolePageQo) {
        log.info(JSONObject.toJSONString(webRolePageQo));
        IPage<WebRoleVo> result = userService.queryPageRole(webRolePageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<WebRoleVo>>().setObj(userService.queryPageRole(webRolePageQo));
    }

    @ApiOperation(value = "保存外网角色")
    @PostMapping(value = "/saveRole")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRole')")
    public Result<String> saveRole(@RequestBody @Valid SaveWebRoleMo saveWebRoleMo) {
        log.info(JSONObject.toJSONString(saveWebRoleMo));
        String result = userService.saveRole(saveWebRoleMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

<<<<<<< HEAD

=======
    @ApiOperation(value = "查询外网路由")
    @PostMapping(value = "/queryListMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:queryListMenu')")
    public Result<List<WebMenuVo>> queryListMenu(@RequestBody @Valid WebMenuPageQo webMenuPageQo) {
        log.info(JSONObject.toJSONString(webMenuPageQo));
        List<WebMenuVo> result = userService.queryListMenu(webMenuPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<WebMenuVo>>().setObj(result);
    }
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df


    @ApiOperation(value = "保存外网路由")
    @PostMapping(value = "/saveMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:saveMenu')")
    public Result<String> saveMenu(@RequestBody @Valid SaveWebMenuMo saveWebMenuMo) {
        log.info(JSONObject.toJSONString(saveWebMenuMo));
        String result = userService.saveMenu(saveWebMenuMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

<<<<<<< HEAD
    @ApiOperation(value = "查询外网路由")
    @PostMapping(value = "/queryListMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:queryListMenu')")
    public Result<List<WebMenuVo>> queryListMenu(@RequestBody @Valid WebMenuPageQo webMenuPageQo) {
        log.info(JSONObject.toJSONString(webMenuPageQo));
        List<WebMenuVo> result = userService.queryListMenu(webMenuPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<WebMenuVo>>().setObj(result);
    }
=======

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    @ApiOperation(value = "保存外网角色和路由的关系")
    @PostMapping(value = "/saveRoleAndMenu")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRoleAndMenu')")
    public Result<String> saveRoleAndMenu(@RequestBody @Valid SaveRoleAndMenuMo saveRoleAndMenuMo) {
        log.info(JSONObject.toJSONString(saveRoleAndMenuMo));
        String result = userService.saveRoleAndMenu(saveRoleAndMenuMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

<<<<<<< HEAD


    @ApiOperation(value = "保存外网权限")
    @PostMapping(value = "/savePermission")
    @PreAuthorize(value = "hasAuthority('sys:user:savePermission')")
    public Result<String> savePermission(@RequestBody @Valid SaveWebPermissionMo saveWebPermissionMo) {
        log.info(JSONObject.toJSONString(saveWebPermissionMo));
        String result = userService.savePermission(saveWebPermissionMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }
=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    @ApiOperation(value = "分页查询外网权限")
    @PostMapping(value = "/queryPagePermission")
    @PreAuthorize(value = "hasAuthority('sys:user:queryPagePermission')")
    public Result<IPage<WebPermissionVo>> queryPagePermission(@RequestBody @Valid WebPermissionPageQo webPermissionPageQo) {
        log.info(JSONObject.toJSONString(webPermissionPageQo));
        IPage<WebPermissionVo> result = userService.queryPagePermission(webPermissionPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<IPage<WebPermissionVo>>().setObj(result);
    }
<<<<<<< HEAD
    @ApiOperation(value = "分页查询外网权限")
    @PostMapping(value = "/queryListPermission")
    @PreAuthorize(value = "hasAuthority('sys:user:queryListPermission')")
    public Result<List<WebPermissionVo>> queryListPermission(@RequestBody @Valid WebPermissionPageQo webPermissionPageQo) {
        log.info(JSONObject.toJSONString(webPermissionPageQo));
        List<WebPermissionVo> result = userService.queryListPermission(webPermissionPageQo);
        log.info(JSONObject.toJSONString(result));
        return new Result<List<WebPermissionVo>>().setObj(result);
    }

    @ApiOperation(value = "保存外网角色和路由的关系")
    @PostMapping(value = "/saveRoleAndPermission")
    @PreAuthorize(value = "hasAuthority('sys:user:saveRoleAndPermission')")
    public Result<String> saveRoleAndPermission(@RequestBody @Valid SaveRoleAndPermissionMo saveRoleAndPermissionMo) {
        log.info(JSONObject.toJSONString(saveRoleAndPermissionMo));
        String result = userService.saveRoleAndPermission(saveRoleAndPermissionMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
=======

    @ApiOperation(value = "保存外网权限")
    @PostMapping(value = "/savePermission")
    @PreAuthorize(value = "hasAuthority('sys:user:savePermission')")
    public Result<WebPermissionVo> savePermission(@RequestBody @Valid SaveWebPermissionMo saveWebPermissionMo) {
        log.info(JSONObject.toJSONString(saveWebPermissionMo));
        WebPermissionVo result = null;
        log.info(JSONObject.toJSONString(result));
        return new Result<WebPermissionVo>().setObj(result);
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
    }


}
