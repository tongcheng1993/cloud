package com.zifuji.cloud.server.sys.module.manageUser.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.mo.LoginMo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.vo.DrawCaptchaVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageMenuVo;
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
    public Result<DrawCaptchaVo> drawCaptcha() {

        DrawCaptchaVo result = manageUserService.drawCaptcha();

        return new Result<DrawCaptchaVo>().setObj(result);
    }

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginMo loginMo) {

        String result = manageUserService.login(loginMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "获取路由")
    @GetMapping(value = "/getMenu")
    public Result<List<ManageMenuVo>> getMenu() {

        List<ManageMenuVo> result = manageUserService.getMenu();

        return new Result<List<ManageMenuVo>>().setObj(result);
    }


}
