package com.zifuji.cloud.server.business.module.webUser.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.LoginMo;
import com.zifuji.cloud.server.business.module.webUser.controller.mo.RegisterMo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.DrawCaptchaVo;
import com.zifuji.cloud.server.business.module.webUser.controller.vo.GetMenuVo;
import com.zifuji.cloud.server.business.module.webUser.service.UserService;
import com.zifuji.cloud.server.business.module.webUser.service.vo.DrawCaptchaServiceVo;
import com.zifuji.cloud.server.business.module.webUser.service.mo.LoginServiceMo;
import com.zifuji.cloud.server.business.module.webUser.service.mo.RegisterServiceMo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "注册用户控制器")
@RestController
@RequestMapping(value = "/webUser")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "获取路由")
    @PostMapping(value = "/getMenu")
    public Result<List<GetMenuVo>> getMenu() {
        return Result.setObj(userService.getMenu().stream().map(webMenuServiceVo -> {
            GetMenuVo vo = new GetMenuVo();
            BeanUtil.copyProperties(webMenuServiceVo, vo);
            return vo;
        }).collect(Collectors.toList()));
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "根据验证码ID获取图片")
    @PostMapping(value = "/drawCaptcha")
    public Result<DrawCaptchaVo> drawCaptcha() {
        DrawCaptchaServiceVo bo = userService.drawCaptcha();
        DrawCaptchaVo vo = new DrawCaptchaVo();
        if (ObjectUtil.isNotNull(bo)) {
            BeanUtil.copyProperties(bo, vo);
        }
        return Result.setObj(vo);
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginMo loginMo) {
        LoginServiceMo loginServiceMo = new LoginServiceMo();
        BeanUtil.copyProperties(loginMo, loginServiceMo);
        return Result.setObj(userService.login(loginServiceMo));
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "退出")
    @PostMapping(value = "/logout")
    @PreAuthorize(value = "hasAuthority('business:webUser:logout')")
    public Result<Boolean> logout() {
        return Result.setObj(userService.logout());
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "重置后端心跳")
    @PostMapping(value = "/refreshHeartbeat")
    @PreAuthorize(value = "hasAuthority('business:webUser:logout')")
    public Result<Boolean> refreshHeartbeat() {
        return Result.setObj(true);
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "刷新token，将新token返回")
    @PostMapping(value = "/refreshToken")
    @PreAuthorize(value = "hasAuthority('business:webUser:refreshToken')")
    public Result<String> refreshToken() {
        return Result.setObj("");
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "账号密码注册")
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody @Valid RegisterMo registerMo) {
        RegisterServiceMo registerServiceMo = new RegisterServiceMo();
        BeanUtil.copyProperties(registerMo, registerServiceMo);
        return Result.setObj(userService.register(registerServiceMo));
    }


}
