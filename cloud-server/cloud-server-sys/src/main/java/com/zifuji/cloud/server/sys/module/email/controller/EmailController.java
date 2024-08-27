 package com.zifuji.cloud.server.sys.module.email.controller;


import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.sys.module.email.controller.mo.SendEmailMo;
import com.zifuji.cloud.server.sys.module.email.controller.vo.EmailRecordVo;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;


@Api(tags = "email控制器")
@Slf4j
@RestController
@RequestMapping(value = "/email")
@AllArgsConstructor
public class EmailController {


    private EmailService emailService;

    @PostMapping("/sendEmail")
    public Result<EmailRecordVo> sendEmail(@RequestBody SendEmailMo sendEmailMo) {
      return Result.setObj(emailService.sendEmail(sendEmailMo));
    }

}
