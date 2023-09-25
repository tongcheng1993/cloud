package com.zifuji.cloud.server.sys.module.email.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.email.mo.SaveEmailTemplateMo;

import com.zifuji.cloud.server.sys.module.email.mo.SendEmailSimpleControllerMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailTemplateControllerMo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailRecordPageQo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailTemplatePageQo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailAccountControllerVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailRecordControllerVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailTemplateControllerVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface EmailService {

    EmailAccountControllerVo getEmailAccount();

    String saveEmailTemplate(SaveEmailTemplateMo saveEmailTemplateMo);

    EmailRecordControllerVo sendEmailByTemplate(SendEmailTemplateControllerMo sendEmailTemplateMo);

    EmailRecordControllerVo sendEmail(SendEmailSimpleControllerMo sendEmailSimpleMo);

    IPage<EmailRecordControllerVo> queryPageEmailRecord(EmailRecordPageQo emailRecordPageQo);

    IPage<EmailTemplateControllerVo> queryPageEmailTemplate(EmailTemplatePageQo emailTemplatePageQo);

    EmailTemplateControllerVo getEmailTemplateById(String id);



    EmailRecordControllerVo sendEmailTemplateById(String to, String id, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList);

    EmailRecordControllerVo sendEmailTemplateByName(String to, String name, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList);

    EmailRecordControllerVo sendMailNoException(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList);
}
