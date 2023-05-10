package com.zifuji.cloud.server.sys.module.email.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.email.mo.SaveEmailTemplateMo;

import com.zifuji.cloud.server.sys.module.email.mo.SendEmailSimpleMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailTemplateMo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailRecordPageQo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailTemplatePageQo;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import com.zifuji.cloud.server.sys.module.email.vo.EmailAccountVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailRecordVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailTemplateVo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;

@Api(value = "")
@Slf4j
@RestController
@RequestMapping(value = "/email")
@AllArgsConstructor
public class EmailController {


    private EmailService emailService;

    @ApiOperation(value = "获取发件邮箱信息")
    @GetMapping(value = "/getEmailAccount")
    public Result<EmailAccountVo> getEmailAccount() {

        EmailAccountVo result = emailService.getEmailAccount();

        return new Result<EmailAccountVo>().setObj(result);
    }

    @ApiOperation(value = "新增邮件模板")
    @PostMapping(value = "/saveEmailTemplate")
    public Result<String> saveEmailTemplate(@RequestBody @Valid SaveEmailTemplateMo saveEmailTemplateMo) {

        String result = emailService.saveEmailTemplate(saveEmailTemplateMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendEmailByTemplate")
    public Result<EmailRecordVo> sendEmailByTemplate(@RequestBody @Valid SendEmailTemplateMo sendEmailTemplateMo) {

        EmailRecordVo result = emailService.sendEmailByTemplate(sendEmailTemplateMo);

        return new Result<EmailRecordVo>().setObj(result);
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendEmail")
    public Result<EmailRecordVo> sendEmail(@RequestBody @Valid SendEmailSimpleMo sendEmailSimpleMo) {

        EmailRecordVo result = emailService.sendEmail(sendEmailSimpleMo);

        return new Result<EmailRecordVo>().setObj(result);
    }

    @ApiOperation(value = "分页查询邮件发送记录列表")
    @PostMapping(value = "/queryPageEmailRecord")
    public Result<IPage<EmailRecordVo>> queryPageEmailRecord(@RequestBody @Valid EmailRecordPageQo emailRecordPageQo) {

        IPage<EmailRecordVo> result = emailService.queryPageEmailRecord(emailRecordPageQo);

        return new Result<IPage<EmailRecordVo>>().setObj(result);
    }


    @ApiOperation(value = "分页查询邮件模板列表")
    @PostMapping(value = "/queryPageEmailTemplate")
    public Result<IPage<EmailTemplateVo>> queryPageEmailTemplate(@RequestBody @Valid EmailTemplatePageQo emailTemplatePageQo) {

        IPage<EmailTemplateVo> result = emailService.queryPageEmailTemplate(emailTemplatePageQo);

        return new Result<IPage<EmailTemplateVo>>().setObj(result);
    }

    @ApiOperation(value = "查询邮件模板详情")
    @GetMapping(value = "/getEmailTemplateById")
    public Result<EmailTemplateVo> getEmailTemplateById(@RequestParam Long id) {

        EmailTemplateVo result = emailService.getEmailTemplateById(id);

        return new Result<EmailTemplateVo>().setObj(result);
    }
}
