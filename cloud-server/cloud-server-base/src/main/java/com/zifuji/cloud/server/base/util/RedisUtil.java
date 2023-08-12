package com.zifuji.cloud.server.base.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

@Slf4j
public class RedisUtil {

    public static boolean checkCodeAndValue(StringRedisTemplate srt, String code, String value) {
        if (ObjectUtil.isNull(srt)) {
            return false;
        }
        if (StrUtil.isBlank(code)) {
            return false;
        }
        if (StrUtil.isBlank(value)) {
            return false;
        }
        String redisValue = srt.opsForValue().get(code);
        srt.delete(code);
        if (StrUtil.isBlank(redisValue)) {
            return false;
        }
        return StrUtil.equalsIgnoreCase(redisValue, value);
    }

}
