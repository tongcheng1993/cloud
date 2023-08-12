package com.zifuji.cloud.server.business.module.user.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.user.controller.mo.LoginControllerMo;
import com.zifuji.cloud.server.business.module.user.controller.vo.DrawCaptchaControllerVo;
import com.zifuji.cloud.server.business.module.user.controller.vo.GetMenuControllerVo;
import com.zifuji.cloud.server.business.module.user.service.UserService;
import com.zifuji.cloud.server.business.module.user.service.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.business.module.user.service.bo.WebMenuBo;
import com.zifuji.cloud.server.business.module.user.service.mo.LoginMo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "注册用户控制器")
@RestController
@RequestMapping(value = "/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @ApiOperation(value = "获取路由")
    @PostMapping(value = "/getMenu")
    public Result<List<GetMenuControllerVo>> getMenu() {
        List<GetMenuControllerVo> list = new ArrayList<>();
        List<WebMenuBo> webMenuBoList = userService.getMenu();
        if (ObjectUtil.isNotEmpty(webMenuBoList)) {
            list = webMenuBoList.stream().map(webMenuBo -> {
                GetMenuControllerVo vo = new GetMenuControllerVo();
                BeanUtil.copyProperties(webMenuBo, vo);
                return vo;
            }).collect(Collectors.toList());
        }
        return Result.setObj(list);
    }

    @ApiOperation(value = "根据验证码ID获取图片")
    @PostMapping(value = "/drawCaptcha")
    public Result<DrawCaptchaControllerVo> drawCaptcha() {
        DrawCaptchaControllerVo vo = new DrawCaptchaControllerVo();
        DrawCaptchaBo bo = userService.drawCaptcha();
        if (ObjectUtil.isNotNull(bo)) {
            BeanUtil.copyProperties(bo, vo);
        }
        return Result.setObj(vo);
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginControllerMo loginControllerMo) {
        LoginMo loginMo = new LoginMo();
        BeanUtil.copyProperties(loginControllerMo, loginMo);
        return Result.setObj(userService.login(loginMo));
    }

    @ApiOperation(value = "退出")
    @PostMapping(value = "/logout")
    public Result<Boolean> logout() {
        return Result.setObj(userService.logout());
    }
}
