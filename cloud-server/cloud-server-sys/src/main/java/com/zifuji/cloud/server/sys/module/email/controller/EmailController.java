package com.zifuji.cloud.server.sys.module.email.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.email.mo.SaveEmailTemplateControllerMo;

import com.zifuji.cloud.server.sys.module.email.mo.SendEmailSimpleControllerMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailTemplateControllerMo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailRecordPageQo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailTemplatePageQo;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import com.zifuji.cloud.server.sys.module.email.vo.EmailAccountControllerVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailRecordControllerVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailTemplateControllerVo;
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
    public Result<EmailAccountControllerVo> getEmailAccount() {

        EmailAccountControllerVo result = emailService.getEmailAccount();

        return new Result<EmailAccountControllerVo>().setObj(result);
    }

    @ApiOperation(value = "新增邮件模板")
    @PostMapping(value = "/saveEmailTemplate")
    public Result<String> saveEmailTemplate(@RequestBody @Valid SaveEmailTemplateControllerMo saveEmailTemplateMo) {

        String result = emailService.saveEmailTemplate(saveEmailTemplateMo);

        return new Result<String>().setObj(result);
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendEmailByTemplate")
    public Result<EmailRecordControllerVo> sendEmailByTemplate(@RequestBody @Valid SendEmailTemplateControllerMo sendEmailTemplateMo) {

        EmailRecordControllerVo result = emailService.sendEmailByTemplate(sendEmailTemplateMo);

        return new Result<EmailRecordControllerVo>().setObj(result);
    }

    @ApiOperation(value = "发送邮件")
    @PostMapping(value = "/sendEmail")
    public Result<EmailRecordControllerVo> sendEmail(@RequestBody @Valid SendEmailSimpleControllerMo sendEmailSimpleMo) {

        EmailRecordControllerVo result = emailService.sendEmail(sendEmailSimpleMo);

        return new Result<EmailRecordControllerVo>().setObj(result);
    }

    @ApiOperation(value = "分页查询邮件发送记录列表")
    @PostMapping(value = "/queryPageEmailRecord")
    public Result<IPage<EmailRecordControllerVo>> queryPageEmailRecord(@RequestBody @Valid EmailRecordPageQo emailRecordPageQo) {

        IPage<EmailRecordControllerVo> result = emailService.queryPageEmailRecord(emailRecordPageQo);

        return new Result<IPage<EmailRecordControllerVo>>().setObj(result);
    }


    @ApiOperation(value = "分页查询邮件模板列表")
    @PostMapping(value = "/queryPageEmailTemplate")
    public Result<IPage<EmailTemplateControllerVo>> queryPageEmailTemplate(@RequestBody @Valid EmailTemplatePageQo emailTemplatePageQo) {

        IPage<EmailTemplateControllerVo> result = emailService.queryPageEmailTemplate(emailTemplatePageQo);

        return new Result<IPage<EmailTemplateControllerVo>>().setObj(result);
    }

    @ApiOperation(value = "查询邮件模板详情")
    @GetMapping(value = "/getEmailTemplateById")
    public Result<EmailTemplateControllerVo> getEmailTemplateById(@RequestParam Long id) {

        EmailTemplateControllerVo result = emailService.getEmailTemplateById(id);

        return new Result<EmailTemplateControllerVo>().setObj(result);
    }
}
