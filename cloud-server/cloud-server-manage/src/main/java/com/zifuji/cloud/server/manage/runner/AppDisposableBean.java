package com.zifuji.cloud.server.manage.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AppDisposableBean implements DisposableBean  {


    @Override
    public void destroy() throws Exception {
        
        log.info("当系统要关闭时，会自动执行此方法，用来关闭系统数据");

    }
}
