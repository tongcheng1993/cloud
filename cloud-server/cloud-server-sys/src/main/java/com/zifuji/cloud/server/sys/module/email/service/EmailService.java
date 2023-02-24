package com.zifuji.cloud.server.sys.module.email.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.email.mo.SaveEmailTemplateMo;

import com.zifuji.cloud.server.sys.module.email.mo.SendEmailSimpleMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailTemplateMo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailRecordPageQo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailTemplatePageQo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailAccountVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailRecordVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailTemplateVo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface EmailService {

    EmailAccountVo getEmailAccount();

    String saveEmailTemplate(SaveEmailTemplateMo saveEmailTemplateMo);

    EmailRecordVo sendEmailByTemplate(SendEmailTemplateMo sendEmailTemplateMo);

    EmailRecordVo sendEmail(SendEmailSimpleMo sendEmailSimpleMo);

    IPage<EmailRecordVo> queryPageEmailRecord(EmailRecordPageQo emailRecordPageQo);

    IPage<EmailTemplateVo> queryPageEmailTemplate(EmailTemplatePageQo emailTemplatePageQo);

    EmailTemplateVo getEmailTemplateById(Long id);



    EmailRecordVo sendEmailTemplateById(String to, Long id, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList);

    EmailRecordVo sendEmailTemplateByName(String to, String name, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList);

    EmailRecordVo sendMailNoException(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList);
}
