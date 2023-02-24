package com.zifuji.cloud.server.sys.module.email.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.db.email.entity.EmailRecordEntity;
import com.zifuji.cloud.server.sys.db.email.entity.EmailTemplateEntity;
import com.zifuji.cloud.server.sys.db.email.service.EmailRecordEntityService;
import com.zifuji.cloud.server.sys.db.email.service.EmailTemplateEntityService;
import com.zifuji.cloud.server.sys.db.template.entity.TemplateEntity;
import com.zifuji.cloud.server.sys.db.template.service.TemplateEntityService;
import com.zifuji.cloud.server.sys.module.email.bo.EmailRecordBo;
import com.zifuji.cloud.server.sys.module.email.bo.EmailTemplateBo;
import com.zifuji.cloud.server.sys.module.email.component.EmailComponent;
import com.zifuji.cloud.server.sys.module.email.mapper.EmailMapper;
import com.zifuji.cloud.server.sys.module.email.mo.SaveEmailTemplateMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailSimpleMo;
import com.zifuji.cloud.server.sys.module.email.mo.SendEmailTemplateMo;
import com.zifuji.cloud.server.sys.module.email.properties.EmailProperties;
import com.zifuji.cloud.server.sys.module.email.qo.EmailRecordPageQo;
import com.zifuji.cloud.server.sys.module.email.qo.EmailTemplatePageQo;
import com.zifuji.cloud.server.sys.module.email.service.EmailService;
import com.zifuji.cloud.server.sys.module.email.vo.EmailAccountVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailRecordVo;
import com.zifuji.cloud.server.sys.module.email.vo.EmailTemplateVo;
import com.zifuji.cloud.server.sys.module.file.service.FileService;
import com.zifuji.cloud.server.sys.module.template.service.TemplateService;

import com.zifuji.cloud.starter.web.util.MyBatisPlusUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private EmailMapper emailMapper;

    private EmailComponent emailComponent;

    private EmailProperties emailProperties;

    private EmailRecordEntityService emailRecordEntityService;

    private EmailTemplateEntityService emailTemplateEntityService;

    private TemplateEntityService templateEntityService;

    private TemplateService templateService;

    private FileService fileService;


    @Override
    public EmailAccountVo getEmailAccount() {
        EmailAccountVo vo = new EmailAccountVo();
        vo.setName(emailProperties.getUsername());
        return vo;
    }

    @Override
    public String saveEmailTemplate(SaveEmailTemplateMo saveEmailTemplateMo) {
        if (ObjectUtil.isNull(saveEmailTemplateMo.getId())) {
            TemplateEntity templateEntity = new TemplateEntity();
            templateEntity.setContent(saveEmailTemplateMo.getContent());
            templateEntityService.save(templateEntity);
            EmailTemplateEntity emailTemplateEntity = new EmailTemplateEntity();
            emailTemplateEntity.setName(saveEmailTemplateMo.getName());
            emailTemplateEntity.setSubject(saveEmailTemplateMo.getSubject());
            emailTemplateEntity.setTemplateId(templateEntity.getId());
            emailTemplateEntityService.save(emailTemplateEntity);
            return emailTemplateEntity.getId() + "";
        } else {
            EmailTemplateEntity emailTemplateEntity = emailTemplateEntityService.getById(saveEmailTemplateMo.getId());
            if (ObjectUtil.isNull(emailTemplateEntity)) {
                throw new Exception200("");
            }
            emailTemplateEntity.setName(saveEmailTemplateMo.getName());
            emailTemplateEntity.setSubject(saveEmailTemplateMo.getSubject());
            emailTemplateEntityService.updateById(emailTemplateEntity);
            TemplateEntity templateEntity = templateEntityService.getById(emailTemplateEntity.getTemplateId());
            templateEntity.setContent(saveEmailTemplateMo.getContent());
            templateEntityService.updateById(templateEntity);
            return emailTemplateEntity.getId() + "";
        }
    }

    @Override
    public EmailRecordVo sendEmailByTemplate(SendEmailTemplateMo sendEmailTemplateMo) {
        List<MultipartFile> imgList = fileService.getFileList(sendEmailTemplateMo.getImgList());
        List<MultipartFile> fileList = fileService.getFileList(sendEmailTemplateMo.getFileList());
        Map<String, Object> map = JSONObject.parseObject(sendEmailTemplateMo.getParamMapStr(), new TypeReference<Map<String, Object>>() {
        });
        return sendEmailTemplateById(sendEmailTemplateMo.getTo(), sendEmailTemplateMo.getId(), map, imgList, fileList);
    }


    @Override
    public EmailRecordVo sendEmail(SendEmailSimpleMo sendEmailSimpleMo) {
        List<MultipartFile> imgList = fileService.getFileList(sendEmailSimpleMo.getImgList());
        List<MultipartFile> fileList = fileService.getFileList(sendEmailSimpleMo.getFileList());
        return sendMailNoException(sendEmailSimpleMo.getTo(), sendEmailSimpleMo.getSubject(), sendEmailSimpleMo.getContent(), imgList, fileList);
    }

    @Override
    public IPage<EmailRecordVo> queryPageEmailRecord(EmailRecordPageQo emailRecordPageQo) {
        IPage<EmailRecordBo> page = selectPageEmailRecord(emailRecordPageQo);
        return page.convert(bo -> {
            EmailRecordVo vo = new EmailRecordVo();
            BeanUtil.copyProperties(bo, vo);
            return vo;
        });
    }

    @Override
    public IPage<EmailTemplateVo> queryPageEmailTemplate(EmailTemplatePageQo emailTemplatePageQo) {
        IPage<EmailTemplateBo> page = selectPageEmailTemplate(emailTemplatePageQo);
        return page.convert(bo -> {
            EmailTemplateVo vo = new EmailTemplateVo();
            BeanUtil.copyProperties(bo, vo);
            return vo;
        });
    }

    @Override
    public EmailTemplateVo getEmailTemplateById(Long id) {
        EmailTemplateVo vo = new EmailTemplateVo();
        EmailTemplateEntity emailTemplateEntity = emailTemplateEntityService.getById(id);
        BeanUtil.copyProperties(emailTemplateEntity, vo);
        TemplateEntity templateEntity = templateEntityService.getById(emailTemplateEntity.getTemplateId());
        vo.setContent(templateEntity.getContent());
        return vo;
    }


    @Override
    public EmailRecordVo sendEmailTemplateById(String to, Long id, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList) {
        EmailTemplateEntity emailTemplateEntity = emailTemplateEntityService.getById(id);
        String subject = emailTemplateEntity.getSubject();
        Long templateId = emailTemplateEntity.getTemplateId();
        String content = templateService.generateTemplateById(templateId, map);
        return sendMailNoException(to, subject, content, imgList, fileList);
    }
    @Override
    public EmailRecordVo sendEmailTemplateByName(String to, String name, Map<String, Object> map, List<MultipartFile> imgList, List<MultipartFile> fileList) {
        QueryWrapper<EmailTemplateEntity> emailTemplateEntityQueryWrapper = new QueryWrapper<>();
        emailTemplateEntityQueryWrapper.lambda().eq(EmailTemplateEntity::getName,name);
        EmailTemplateEntity emailTemplateEntity = emailTemplateEntityService.getOne(emailTemplateEntityQueryWrapper);
        String subject = emailTemplateEntity.getSubject();
        Long templateId = emailTemplateEntity.getTemplateId();
        String content = templateService.generateTemplateById(templateId, map);
        return sendMailNoException(to, subject, content, imgList, fileList);
    }


    @Override
    public EmailRecordVo sendMailNoException(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList) {
        Boolean flag = emailComponent.sendMailNoException(to, subject, content, imgList, fileList);
        EmailRecordEntity emailRecordEntity = new EmailRecordEntity();
        emailRecordEntity.setAddrFrom(emailProperties.getUsername());
        emailRecordEntity.setAddrTo(to);
        emailRecordEntity.setSubject(subject);
        emailRecordEntity.setContent(content);
        if (flag) {
            emailRecordEntity.setSendStatus("1");
            emailRecordEntity.setSendTime(LocalDateTime.now());
        } else {
            emailRecordEntity.setSendStatus("0");
        }
        emailRecordEntityService.save(emailRecordEntity);
        EmailRecordVo emailRecordVo = new EmailRecordVo();
        BeanUtil.copyProperties(emailRecordEntity, emailRecordVo);
        return emailRecordVo;
    }


    private IPage<EmailRecordBo> selectPageEmailRecord(EmailRecordPageQo emailRecordPageQo) {
        Page<EmailRecordBo> page = new Page<EmailRecordBo>(emailRecordPageQo.getCurrent(), emailRecordPageQo.getSize());
        QueryWrapper<EmailRecordBo> ew = new QueryWrapper<>();

        MyBatisPlusUtil.orderWrapper(ew, emailRecordPageQo.getOrders());

        ew.eq(StrUtil.isNotBlank(emailRecordPageQo.getAddrTo()), "zser.addr_to", emailRecordPageQo.getAddrTo());
        return emailMapper.selectPageEmailRecord(page, ew);
    }


    private IPage<EmailTemplateBo> selectPageEmailTemplate(EmailTemplatePageQo emailTemplatePageQo) {
        Page<EmailTemplateBo> page = new Page<EmailTemplateBo>(emailTemplatePageQo.getCurrent(), emailTemplatePageQo.getSize());
        QueryWrapper<EmailTemplateBo> ew = new QueryWrapper<>();

        MyBatisPlusUtil.orderWrapper(ew, emailTemplatePageQo.getOrders());

        return emailMapper.selectPageEmailTemplate(page, ew);
    }
}
