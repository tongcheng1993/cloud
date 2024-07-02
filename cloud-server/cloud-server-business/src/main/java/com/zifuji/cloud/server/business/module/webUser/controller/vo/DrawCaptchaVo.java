package com.zifuji.cloud.server.business.module.webUser.controller.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DrawCaptchaVo {

    private String redisUuid;

    private byte[] imgBytes;
}
