package com.zifuji.cloud.server.base.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.jwt.JWTUtil;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.base.bean.BaseConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.zifuji.cloud.server.base.feign")
public class CommonFeignConfig implements RequestInterceptor {

    public CommonFeignConfig(){
        log.info("CommonFeignConfig");
    }


    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(attributes)) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
        } else {
            UserInfo userInfo = null;
            Map<String, Object> map = new HashMap<String, Object>();
            // 设置游客身份信息
            userInfo = new UserInfo();
            map.put("userInfo", userInfo);
            // 网关通过后 在请求中增加 内部token
            String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
            String name = "X-Access-Token";
            String values = token;
            template.header(name, values);
        }

    }
}
