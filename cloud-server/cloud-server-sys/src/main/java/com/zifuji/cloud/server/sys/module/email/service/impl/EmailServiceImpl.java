package com.zifuji.cloud.server.sys.module.email.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zifuji.cloud.server.sys.db.email.entity.EmailRecordEntity;
import com.zifuji.cloud.server.sys.db.email.service.EmailRecordEntityService;
import com.zifuji.cloud.server.sys.db.email.service.EmailTemplateEntityService;
import com.zifuji.cloud.server.sys.module.email.component.EmailComponent;
import com.zifuji.cloud.server.sys.module.email.mapper.EmailMapper;
import com.zifuji.cloud.server.sys.module.email.controller.mo.SendEmailMo;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import com.zifuji.cloud.server.sys.module.email.controller.vo.EmailRecordVo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private EmailMapper emailMapper;

    private EmailComponent emailComponent;

    private EmailRecordEntityService emailRecordEntityService;

    private EmailTemplateEntityService emailTemplateEntityService;

    private FileService fileService;


    @Override
    public EmailRecordVo sendEmail(SendEmailMo sendEmailMo) {
        List<MultipartFile> imgList = fileService.getFileList(sendEmailMo.getImgList());
        List<MultipartFile> fileList = fileService.getFileList(sendEmailMo.getFileList());
        EmailRecordEntity emailRecordEntity = sendHtmlMailNoException(sendEmailMo.getTo(), sendEmailMo.getSubject(), sendEmailMo.getContent(), imgList, fileList);
        EmailRecordVo emailRecordVo = new EmailRecordVo();
        BeanUtil.copyProperties(emailRecordEntity, emailRecordVo);
        return emailRecordVo;
    }


    private EmailRecordEntity sendHtmlMailNoException(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList) {
        EmailRecordEntity emailRecordEntity = new EmailRecordEntity();
        Boolean flag = emailComponent.sendHtmlMailNoException(to, subject, content, imgList, fileList);
        emailRecordEntity.setAddrTo(to);
        emailRecordEntity.setSubject(subject);
        emailRecordEntity.setContent(content);
        emailRecordEntity.setSendStatus(flag);
        emailRecordEntity.setSendTime(LocalDateTime.now());
        emailRecordEntityService.save(emailRecordEntity);
        return emailRecordEntity;
    }
}
