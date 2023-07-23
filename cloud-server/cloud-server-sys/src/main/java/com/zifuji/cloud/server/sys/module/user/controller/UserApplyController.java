package com.zifuji.cloud.server.sys.module.user.controller;

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
    public Result<List<WebMenuControllerVo>> getMenu() {

        List<WebMenuControllerVo> result = userService.getMenu();

        return new Result<List<WebMenuControllerVo>>().setObj(result);
    }

    @ApiOperation(value = "根据验证码ID获取图片")
    @GetMapping(value = "/drawCaptcha")
    public Result<WebDrawCaptchaControllerVo> drawCaptcha() {

        WebDrawCaptchaControllerVo result = userService.drawCaptcha();

        return new Result<WebDrawCaptchaControllerVo>().setObj(result);
    }

    @ApiOperation(value = "账号密码注册")
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody @Valid RegisterControllerMo registerMo) {

        String result = userService.register(registerMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> loginByUserName(@RequestBody @Valid LoginControllerMo loginMo) {

        String result = userService.login(loginMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "获取自身信息")
    @GetMapping(value = "/getUserInfo")
    public Result<WebUserControllerVo> getUserInfo() {

        WebUserControllerVo result = userService.getUserInfo();

        return new Result<WebUserControllerVo>().setObj(result);
    }


    @ApiOperation(value = "保存实名信息")
    @PostMapping(value = "/savePeopleInfo")
    public Result<WebPeopleControllerVo> savePeopleInfo(@RequestBody @Valid SavePeopleInfoControllerMo savePeopleInfoMo) {

        WebPeopleControllerVo result = userService.savePeopleInfo(savePeopleInfoMo);

        return new Result<WebPeopleControllerVo>().setObj(result);
    }

    @ApiOperation(value = "获取实名信息")
    @GetMapping(value = "/getPeopleInfo")
    public Result<WebPeopleControllerVo> getPeopleInfo() {

        WebPeopleControllerVo result = userService.getPeopleInfo();

        return new Result<WebPeopleControllerVo>().setObj(result);
    }


    @ApiOperation(value = "保存公司信息")
    @PostMapping(value = "/saveCompanyInfo")
    public Result<WebCompanyControllerVo> saveCompanyInfo(@RequestBody @Valid SaveCompanyInfoControllerMo saveCompanyInfoMo) {

        WebCompanyControllerVo result = userService.saveCompanyInfo(saveCompanyInfoMo);

        return new Result<WebCompanyControllerVo>().setObj(result);
    }


    @ApiOperation(value = "获取公司信息")
    @GetMapping(value = "/getCompanyInfo")
    public Result<WebCompanyControllerVo> getCompanyInfo() {

        WebCompanyControllerVo result = userService.getCompanyInfo();

        return new Result<WebCompanyControllerVo>().setObj(result);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/changePassWord")
    public Result<String> changePassWord(@RequestBody @Valid ChangePassWordControllerMo changePassWordMo) {

        String result = userService.changePassWord(changePassWordMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "发送绑定邮箱验证码")
    @PostMapping(value = "/sendBindEmailCaptcha")
    public Result<String> sendBindEmailCaptcha(@RequestBody @Valid SendBindEmailCaptchaControllerMo sendBindEmailCaptchaMo) {

        String result = userService.sendBindEmailCaptcha(sendBindEmailCaptchaMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "绑定邮箱")
    @PostMapping(value = "/saveBindEmail")
    public Result<Boolean> saveBindEmail(@RequestBody @Valid SaveBindEmailControllerMo saveBindEmailMo) {

        Boolean result = userService.saveBindEmail(saveBindEmailMo);

        return new Result<Boolean>().setObj(result);
    }

    @ApiOperation(value = "发送绑定手机号证码")
    @PostMapping(value = "/sendBindPhoneCaptcha")
    public Result<String> sendBindPhoneCaptcha(@RequestBody @Valid SendBindPhoneCaptchaControllerMo sendBindPhoneCaptchaMo) {

        String result = userService.sendBindPhoneCaptcha(sendBindPhoneCaptchaMo);

        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "绑定手机号")
    @PostMapping(value = "/saveBindPhone")
    public Result<Boolean> saveBindPhone(@RequestBody @Valid SaveBindPhoneControllerMo saveBindPhoneMo){

        Boolean result = userService.saveBindPhone(saveBindPhoneMo);

        return new Result<Boolean>().setObj(result);
    }


    @ApiOperation(value = "发送忘记密码验证码")
    @PostMapping(value = "/sendForgetPassWordCaptcha")
    public Result<String> sendForgetPassWordCaptcha(@RequestBody @Valid SendForgetPassWordCaptchaControllerMo sendForgetPassWordCaptchaMo) {

        String result = userService.sendForgetPassWordCaptcha(sendForgetPassWordCaptchaMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "重置忘记密码")
    @PostMapping(value = "/resetForgetPassWord")
    public Result<Boolean> resetForgetPassWord(@RequestBody @Valid ResetForgetPassWordControllerMo resetForgetPassWordMo) {

        Boolean result = userService.resetForgetPassWord(resetForgetPassWordMo);

        return new Result<Boolean>().setObj(result);
    }


}
