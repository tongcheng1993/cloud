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
<<<<<<< HEAD
import org.springframework.security.access.prepost.PreAuthorize;
=======
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Api(value = "user")
@RestController
@RequestMapping(value = "/user/apply")
@AllArgsConstructor
public class UserApplyController {

    private UserService userService;


    @ApiOperation(value = "获取路由")
    @GetMapping(value = "/getMenu")
    public Result<List<WebMenuVo>> getMenu() {
        log.info(JSONObject.toJSONString("空参数"));
        List<WebMenuVo> result = userService.getMenu();
        log.info(JSONObject.toJSONString(result));
        return new Result<List<WebMenuVo>>().setObj(result);
    }

    @ApiOperation(value = "根据验证码ID获取图片")
    @GetMapping(value = "/drawCaptcha")
    public Result<WebDrawCaptchaVo> drawCaptcha() {
        log.info(JSONObject.toJSONString("空参数"));
        WebDrawCaptchaVo result = userService.drawCaptcha();
        log.info(JSONObject.toJSONString(result));
        return new Result<WebDrawCaptchaVo>().setObj(result);
    }

    @ApiOperation(value = "账号密码注册")
    @PostMapping(value = "/register")
    public Result<String> register(@RequestBody @Valid RegisterMo registerMo) {
        log.info(JSONObject.toJSONString(registerMo));
        String result = userService.register(registerMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/login")
    public Result<String> loginByUserName(@RequestBody @Valid LoginMo loginMo) {
        log.info(JSONObject.toJSONString(loginMo));
        String result = userService.login(loginMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "获取自身信息")
    @GetMapping(value = "/getUserInfo")
    public Result<WebUserVo> getUserInfo() {
        log.info(JSONObject.toJSONString("空参数"));
        WebUserVo result = userService.getUserInfo();
        log.info(JSONObject.toJSONString(result));
        return new Result<WebUserVo>().setObj(result);
    }


    @ApiOperation(value = "保存实名信息")
    @PostMapping(value = "/savePeopleInfo")
    public Result<WebPeopleVo> savePeopleInfo(@RequestBody @Valid SavePeopleInfoMo savePeopleInfoMo) {
        log.info(JSONObject.toJSONString(savePeopleInfoMo));
        WebPeopleVo result = userService.savePeopleInfo(savePeopleInfoMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<WebPeopleVo>().setObj(result);
    }

    @ApiOperation(value = "获取实名信息")
    @GetMapping(value = "/getPeopleInfo")
    public Result<WebPeopleVo> getPeopleInfo() {
        log.info(JSONObject.toJSONString("空参数"));
        WebPeopleVo result = userService.getPeopleInfo();
        log.info(JSONObject.toJSONString(result));
        return new Result<WebPeopleVo>().setObj(result);
    }


    @ApiOperation(value = "保存公司信息")
    @PostMapping(value = "/saveCompanyInfo")
    public Result<WebCompanyVo> saveCompanyInfo(@RequestBody @Valid SaveCompanyInfoMo saveCompanyInfoMo) {
        log.info(JSONObject.toJSONString(saveCompanyInfoMo));
        WebCompanyVo result = userService.saveCompanyInfo(saveCompanyInfoMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<WebCompanyVo>().setObj(result);
    }


    @ApiOperation(value = "获取公司信息")
    @GetMapping(value = "/getCompanyInfo")
    public Result<WebCompanyVo> getCompanyInfo() {
        log.info(JSONObject.toJSONString("空参数"));
        WebCompanyVo result = userService.getCompanyInfo();
        log.info(JSONObject.toJSONString(result));
        return new Result<WebCompanyVo>().setObj(result);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/changePassWord")
    public Result<String> changePassWord(@RequestBody @Valid ChangePassWordMo changePassWordMo) {
        log.info(JSONObject.toJSONString(changePassWordMo));
        String result = userService.changePassWord(changePassWordMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "修改昵称")
    @PostMapping(value = "/saveName")
    public Result<Boolean> saveName(@RequestBody @Valid SaveNameMo saveNameMo) {
        log.info(JSONObject.toJSONString(saveNameMo));
        Boolean result = userService.saveName(saveNameMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }

    @ApiOperation(value = "发送绑定邮箱验证码")
    @PostMapping(value = "/sendBindEmailCaptcha")
    public Result<String> sendBindEmailCaptcha(@RequestBody @Valid SendBindEmailCaptchaMo sendBindEmailCaptchaMo) {
        log.info(JSONObject.toJSONString(sendBindEmailCaptchaMo));
        String result = userService.sendBindEmailCaptcha(sendBindEmailCaptchaMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "绑定邮箱")
    @PostMapping(value = "/saveBindEmail")
    public Result<Boolean> saveBindEmail(@RequestBody @Valid SaveBindEmailMo saveBindEmailMo) {
        log.info(JSONObject.toJSONString(saveBindEmailMo));
        Boolean result = userService.saveBindEmail(saveBindEmailMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }

    @ApiOperation(value = "发送绑定邮箱验证码")
    @PostMapping(value = "/sendBindPhoneCaptcha")
    public Result<String> sendBindPhoneCaptcha(@RequestBody @Valid SendBindPhoneCaptchaMo sendBindPhoneCaptchaMo) {
        log.info(JSONObject.toJSONString(sendBindPhoneCaptchaMo));
        String result = userService.sendBindPhoneCaptcha(sendBindPhoneCaptchaMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }


    @ApiOperation(value = "绑定手机号")
    @PostMapping(value = "/saveBindPhone")
    public Result<Boolean> saveBindPhone(@RequestBody @Valid SaveBindPhoneMo saveBindPhoneMo){
        log.info(JSONObject.toJSONString(saveBindPhoneMo));
        Boolean result = userService.saveBindPhone(saveBindPhoneMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }


    @ApiOperation(value = "发送忘记密码验证码")
    @PostMapping(value = "/sendForgetPassWordCaptcha")
    public Result<String> sendForgetPassWordCaptcha(@RequestBody @Valid SendForgetPassWordCaptchaMo sendForgetPassWordCaptchaMo) {
        log.info(JSONObject.toJSONString(sendForgetPassWordCaptchaMo));
        String result = userService.sendForgetPassWordCaptcha(sendForgetPassWordCaptchaMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "重置忘记密码")
    @PostMapping(value = "/resetForgetPassWord")
    public Result<Boolean> resetForgetPassWord(@RequestBody @Valid ResetForgetPassWordMo resetForgetPassWordMo) {
        log.info(JSONObject.toJSONString(resetForgetPassWordMo));
        Boolean result = userService.resetForgetPassWord(resetForgetPassWordMo);
        log.info(JSONObject.toJSONString(result));
        return new Result<Boolean>().setObj(result);
    }


}
