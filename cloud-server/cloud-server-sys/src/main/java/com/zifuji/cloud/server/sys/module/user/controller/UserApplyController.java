package com.zifuji.cloud.server.sys.module.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.user.mo.*;
import com.zifuji.cloud.server.sys.module.user.service.UserService;
import com.zifuji.cloud.server.sys.module.user.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "用户/应用")
@RestController
@RequestMapping(value = "/user/apply")
@AllArgsConstructor
public class UserApplyController {

    private UserService userService;


    @ApiOperation(value = "获取路由")
    @GetMapping(value = "/getMenu")
    public Result<List<WebMenuVo>> getMenu() {

        List<WebMenuVo> result = userService.getMenu();

        return new Result<List<WebMenuVo>>().setObj(result);
    }

    @ApiOperation(value = "根据验证码ID获取图片")
    @GetMapping(value = "/drawCaptcha")
    public Result<WebDrawCaptchaVo> drawCaptcha() {

        WebDrawCaptchaVo result = userService.drawCaptcha();

        return new Result<WebDrawCaptchaVo>().setObj(result);
    }

    @ApiOperation(value = "账号密码注册")
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody @Valid RegisterMo registerMo) {

        String result = userService.register(registerMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> loginByUserName(@RequestBody @Valid LoginMo loginMo) {

        String result = userService.login(loginMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "获取自身信息")
    @GetMapping(value = "/getUserInfo")
    public Result<WebUserVo> getUserInfo() {

        WebUserVo result = userService.getUserInfo();

        return new Result<WebUserVo>().setObj(result);
    }


    @ApiOperation(value = "保存实名信息")
    @PostMapping(value = "/savePeopleInfo")
    public Result<WebPeopleVo> savePeopleInfo(@RequestBody @Valid SavePeopleInfoMo savePeopleInfoMo) {

        WebPeopleVo result = userService.savePeopleInfo(savePeopleInfoMo);

        return new Result<WebPeopleVo>().setObj(result);
    }

    @ApiOperation(value = "获取实名信息")
    @GetMapping(value = "/getPeopleInfo")
    public Result<WebPeopleVo> getPeopleInfo() {

        WebPeopleVo result = userService.getPeopleInfo();

        return new Result<WebPeopleVo>().setObj(result);
    }


    @ApiOperation(value = "保存公司信息")
    @PostMapping(value = "/saveCompanyInfo")
    public Result<WebCompanyVo> saveCompanyInfo(@RequestBody @Valid SaveCompanyInfoMo saveCompanyInfoMo) {

        WebCompanyVo result = userService.saveCompanyInfo(saveCompanyInfoMo);

        return new Result<WebCompanyVo>().setObj(result);
    }


    @ApiOperation(value = "获取公司信息")
    @GetMapping(value = "/getCompanyInfo")
    public Result<WebCompanyVo> getCompanyInfo() {

        WebCompanyVo result = userService.getCompanyInfo();

        return new Result<WebCompanyVo>().setObj(result);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/changePassWord")
    public Result<String> changePassWord(@RequestBody @Valid ChangePassWordMo changePassWordMo) {

        String result = userService.changePassWord(changePassWordMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "发送绑定邮箱验证码")
    @PostMapping(value = "/sendBindEmailCaptcha")
    public Result<String> sendBindEmailCaptcha(@RequestBody @Valid SendBindEmailCaptchaMo sendBindEmailCaptchaMo) {

        String result = userService.sendBindEmailCaptcha(sendBindEmailCaptchaMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "绑定邮箱")
    @PostMapping(value = "/saveBindEmail")
    public Result<Boolean> saveBindEmail(@RequestBody @Valid SaveBindEmailMo saveBindEmailMo) {

        Boolean result = userService.saveBindEmail(saveBindEmailMo);

        return new Result<Boolean>().setObj(result);
    }

    @ApiOperation(value = "发送绑定手机号证码")
    @PostMapping(value = "/sendBindPhoneCaptcha")
    public Result<String> sendBindPhoneCaptcha(@RequestBody @Valid SendBindPhoneCaptchaMo sendBindPhoneCaptchaMo) {

        String result = userService.sendBindPhoneCaptcha(sendBindPhoneCaptchaMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "绑定手机号")
    @PostMapping(value = "/saveBindPhone")
    public Result<Boolean> saveBindPhone(@RequestBody @Valid SaveBindPhoneMo saveBindPhoneMo){

        Boolean result = userService.saveBindPhone(saveBindPhoneMo);

        return new Result<Boolean>().setObj(result);
    }


    @ApiOperation(value = "发送忘记密码验证码")
    @PostMapping(value = "/sendForgetPassWordCaptcha")
    public Result<String> sendForgetPassWordCaptcha(@RequestBody @Valid SendForgetPassWordCaptchaMo sendForgetPassWordCaptchaMo) {

        String result = userService.sendForgetPassWordCaptcha(sendForgetPassWordCaptchaMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "重置忘记密码")
    @PostMapping(value = "/resetForgetPassWord")
    public Result<Boolean> resetForgetPassWord(@RequestBody @Valid ResetForgetPassWordMo resetForgetPassWordMo) {

        Boolean result = userService.resetForgetPassWord(resetForgetPassWordMo);

        return new Result<Boolean>().setObj(result);
    }


}
