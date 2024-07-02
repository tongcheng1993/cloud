package com.zifuji.cloud.server.sys.module.dashboard.service.impl;

import com.zifuji.cloud.server.sys.module.dashboard.service.DashboardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@AllArgsConstructor
public class DashboardServiceImpl implements DashboardService {


    private WebApplicationContext webApplicationContext;

    @Override
    public String info() {
        log.info("info");
        return "欢迎光临";
    }

    @Override
    public String getParameter() {
        RequestMappingHandlerMapping mappingHandlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo requestMappingInfo : map.keySet()) {
            Set<String> urlSet = requestMappingInfo.getPatternsCondition().getPatterns();
            for (String url : urlSet) {
                log.info(url);
            }
            Set<RequestMethod> requestMethodSet = requestMappingInfo.getMethodsCondition().getMethods();
            for (RequestMethod requestMethod : requestMethodSet) {
                log.info(requestMethod.toString());
            }

        }
        return null;
    }
}
