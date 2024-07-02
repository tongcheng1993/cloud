package com.zifuji.cloud.server.sys.module.email.service;


import com.zifuji.cloud.server.sys.module.email.controller.mo.SendEmailMo;
import com.zifuji.cloud.server.sys.module.email.controller.vo.EmailRecordVo;

public interface EmailService {

    EmailRecordVo sendEmail(SendEmailMo sendEmailMo);
}
