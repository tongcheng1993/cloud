package com.zifuji.cloud.server.sys.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Slf4j
@Component
@AllArgsConstructor
public class AppRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("当系统启动时，会自动执行此方法，用来初始化系统数据");
        Set<String> set = args.getOptionNames();
        set.forEach(name -> {
            log.info("{}:{}", name, args.getOptionValues(name));
        });
    }
}
