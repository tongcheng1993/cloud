package com.zifuji.cloud.server.base.module.file.config;

import com.zifuji.cloud.server.base.module.file.bean.ZfjTmpBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ZfjFileConfig {

    @Bean
    public ZfjTmpBean getZfjTmpBean() {
        return new ZfjTmpBean();
    }
}
