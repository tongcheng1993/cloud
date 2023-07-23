package com.zifuji.cloud.server.sys.module.captcha.bo;

import cn.hutool.captcha.ICaptcha;
import com.zifuji.cloud.base.bean.component.BaseComponentMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class DrawCaptchaComponentMo extends BaseComponentMo {

    private String businessCode;

    private String businessId;

    private String redisUuid;

    private byte[] imgBytes;

    private ICaptcha iCaptcha;
}