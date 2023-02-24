package com.zifuji.cloud.server.sys.module.core.bo;

import cn.hutool.captcha.ICaptcha;
import com.zifuji.cloud.base.bean.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class DrawCaptchaBo extends BaseBo {

    private String businessCode;

    private String businessId;

    private String redisUuid;

    private byte[] imgBytes;

    private ICaptcha iCaptcha;
}
