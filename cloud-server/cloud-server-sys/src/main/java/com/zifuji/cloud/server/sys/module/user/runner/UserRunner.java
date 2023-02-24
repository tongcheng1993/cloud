package com.zifuji.cloud.server.sys.module.user.runner;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@AllArgsConstructor
public class UserRunner implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("系统每次启动，都会输出一次，想法是用来做系统授权校验的");
    }
}
