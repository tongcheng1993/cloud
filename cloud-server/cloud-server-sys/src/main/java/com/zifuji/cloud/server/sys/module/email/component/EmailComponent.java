package com.zifuji.cloud.server.sys.module.email.component;


import java.util.List;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.zifuji.cloud.server.sys.module.email.properties.EmailProperties;

import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@AllArgsConstructor
public class EmailComponent {

    private JavaMailSender javaMailSender;

    private EmailProperties emailProperties;

    public Boolean sendMailNoException(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList) {
        try {
            sendHtmlMail(to, subject, content, imgList, fileList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return false;
        }
    }

    private void sendHtmlMail(String to, String subject, String content, List<MultipartFile> imgList, List<MultipartFile> fileList)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        // true表示构建一个可以带附件的邮件对象
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setFrom(emailProperties.getUsername());

        helper.setTo(to);

        helper.setSubject(subject);

        helper.setText(content, true);

        if (ObjectUtil.isNotEmpty(imgList)) {
            for (int i = 0; i < imgList.size(); i++) {
                MultipartFile img = imgList.get(i);
                helper.addInline("img" + i, img, MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(img.getOriginalFilename()));
            }
        }

        if (ObjectUtil.isNotEmpty(fileList)) {
            for (int i = 0; i < fileList.size(); i++) {
                MultipartFile file = fileList.get(i);
                helper.addAttachment(file.getOriginalFilename()+"", file);
            }
        }
        javaMailSender.send(mimeMessage);
    }

}
