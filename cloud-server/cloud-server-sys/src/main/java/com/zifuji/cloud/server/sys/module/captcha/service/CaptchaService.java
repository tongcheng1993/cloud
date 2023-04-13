package com.zifuji.cloud.server.sys.module.captcha.service;

import com.zifuji.cloud.server.sys.module.captcha.bo.DrawCaptchaBo;

public interface CaptchaService {

    DrawCaptchaBo drawCaptcha(String businessCode, String businessId);

    Boolean checkCodeAndValue(String businessCode, String businessId, String redisUuid, String value);
}
