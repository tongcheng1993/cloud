package com.zifuji.cloud.server.sys.module.manageUser.controller;

import cn.hutool.core.bean.BeanUtil;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.manageUser.controller.vo.GetMenuControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.mo.LoginControllerMo;
import com.zifuji.cloud.server.sys.module.manageUser.service.ManageUserService;
import com.zifuji.cloud.server.sys.module.manageUser.controller.vo.DrawCaptchaControllerVo;
import com.zifuji.cloud.server.sys.module.manageUser.vo.ManageMenuControllerVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(tags = "管理端用户控制器")
@RestController
@RequestMapping(value = "/manageUser")
@AllArgsConstructor
public class ManageUserApplyController {
    // 管理端用户服务控制接口
    private ManageUserService manageUserService;

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "根据验证码ID获取图片")
    @PostMapping(value = "/drawCaptcha")
    public Result<DrawCaptchaControllerVo> drawCaptcha() {
        DrawCaptchaControllerVo vo = new DrawCaptchaControllerVo();
        manageUserService.drawCaptcha();
        return Result.setObj(vo);
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = false, paramType = "header")
    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> login(@RequestBody @Valid LoginControllerMo loginMo) {
        return Result.setObj(manageUserService.login(loginMo));
    }

    @ApiImplicitParam(name = "Authorization", dataType = "String", required = true, paramType = "header")
    @ApiOperation(value = "获取路由")
    @PostMapping(value = "/getMenu")
    public Result<List<GetMenuControllerVo>> getMenu() {
        return Result.setObj(manageUserService.getMenu().stream().map(manageMenuControllerVo -> {
            GetMenuControllerVo vo = new GetMenuControllerVo();
            BeanUtil.copyProperties(manageMenuControllerVo, vo);
            return vo;
        }).collect(Collectors.toList()));
    }


}
