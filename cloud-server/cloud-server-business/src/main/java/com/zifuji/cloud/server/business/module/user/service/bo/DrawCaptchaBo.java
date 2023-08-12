package com.zifuji.cloud.server.business.module.user.service.bo;

import cn.hutool.captcha.LineCaptcha;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DrawCaptchaBo {

    private String redisUuid;

    private byte[] imgBytes;

    private LineCaptcha lineCaptcha;
}
