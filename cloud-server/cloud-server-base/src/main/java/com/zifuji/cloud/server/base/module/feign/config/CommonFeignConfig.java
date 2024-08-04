package com.zifuji.cloud.server.base.module.feign.config;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@EnableFeignClients(basePackages = "com.zifuji.cloud.server.base")
public class CommonFeignConfig implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate template) {
    	template.header("From", "Y");
    	boolean flag = true;
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (ObjectUtil.isNotNull(attributes)) {
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if(StrUtil.equals( "X-Access-Token",name)) {
                    	flag = false;
                    }
                    String values = request.getHeader(name);
                    template.header(name, values);
                }
            }
        }
        if(flag) {
        	Map<String, Object> map = new HashMap<String, Object>();
        	UserInfo userInfo = new UserInfo();
        	userInfo.setTableId(-1L);
        	map.put("userInfo", userInfo);
    		// 网关通过后 在请求中增加 内部token
    		String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
        	template.header("X-Access-Token", token);
        }

    }
}
