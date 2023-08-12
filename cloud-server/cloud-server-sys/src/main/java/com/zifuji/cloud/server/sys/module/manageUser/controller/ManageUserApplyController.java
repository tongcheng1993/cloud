package com.zifuji.cloud.server.sys.module.manageUser.controller;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.mo.LoginControllerMo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.DrawCaptchaControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageMenuControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "manageUser/apply")
@RestController
@RequestMapping(value = "/manageUser/apply")
@AllArgsConstructor
public class ManageUserApplyController {

    private ManageUserService manageUserService;

    @ApiOperation(value = "根据验证码ID获取图片")
    @GetMapping(value = "/drawCaptcha")
    public Result<DrawCaptchaControllerVo> drawCaptcha() {

        DrawCaptchaControllerVo result = manageUserService.drawCaptcha();

        return Result.setObj(result);
    }

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginControllerMo loginMo) {

        String result = manageUserService.login(loginMo);

        return Result.setObj(result);
    }

    @ApiOperation(value = "获取路由")
    @GetMapping(value = "/getMenu")
    public Result<List<ManageMenuControllerVo>> getMenu() {

        List<ManageMenuControllerVo> result = manageUserService.getMenu();

        return Result.setObj(result);
    }


}
