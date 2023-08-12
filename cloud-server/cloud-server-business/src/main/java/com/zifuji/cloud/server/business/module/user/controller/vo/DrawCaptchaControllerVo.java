package com.zifuji.cloud.server.business.module.user.controller.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DrawCaptchaControllerVo {
    private String redisUuid;

    private byte[] imgBytes;
}
