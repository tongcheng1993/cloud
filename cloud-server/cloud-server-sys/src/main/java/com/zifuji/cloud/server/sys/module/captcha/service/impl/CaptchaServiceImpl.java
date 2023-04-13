package com.zifuji.cloud.server.sys.module.captcha.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.StrUtil;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.exception.Exception200;
import com.zifuji.cloud.server.sys.module.captcha.bo.DrawCaptchaBo;
import com.zifuji.cloud.server.sys.module.captcha.service.CaptchaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class CaptchaServiceImpl implements CaptchaService {

    private StringRedisTemplate stringRedisTemplate;

    @Override
    public DrawCaptchaBo drawCaptcha(String businessCode, String businessId) {
        DrawCaptchaBo drawCaptchaBo = new DrawCaptchaBo();
        drawCaptchaBo.setBusinessCode(businessCode);
        drawCaptchaBo.setBusinessId(businessId);
        String redisUuid = StrUtil.uuid();
        drawCaptchaBo.setRedisUuid(redisUuid);

        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        RandomGenerator randomGenerator = new RandomGenerator(BaseConstant.STRING_C, 4);
        lineCaptcha.setGenerator(randomGenerator);
        lineCaptcha.createCode();
        drawCaptchaBo.setICaptcha(lineCaptcha);
        drawCaptchaBo.setImgBytes(lineCaptcha.getImageBytes());


        stringRedisTemplate.opsForValue().set(businessCode + businessId + redisUuid, lineCaptcha.getCode(), 60 * 30, TimeUnit.SECONDS);


        return drawCaptchaBo;
    }

    @Override
    public Boolean checkCodeAndValue(String businessCode, String businessId, String redisUuid, String value) {
        // 先校验验证码
        String code = stringRedisTemplate.opsForValue().get(businessCode + businessId + redisUuid);
        stringRedisTemplate.delete(businessCode + businessId + redisUuid);
        if(StrUtil.isBlank(code)){
            throw new Exception200("验证码错误");
        }
        if(!StrUtil.equalsIgnoreCase(code, value)){
            throw new Exception200("验证码错误");
        }
        return true;
    }
}
