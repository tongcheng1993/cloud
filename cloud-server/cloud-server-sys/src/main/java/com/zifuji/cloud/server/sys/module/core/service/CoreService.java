package com.zifuji.cloud.server.sys.module.core.service;

import com.zifuji.cloud.server.sys.module.core.bo.DrawCaptchaBo;

public interface CoreService {

    DrawCaptchaBo drawCaptcha(String businessCode, String businessId);

    Boolean checkCodeAndValue(String businessCode, String businessId, String redisUuid, String value);
}
