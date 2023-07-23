package com.zifuji.cloud.server.sys.module.spider.service.impl;

import com.zifuji.cloud.server.sys.module.spider.component.PageProcessor1;
import com.zifuji.cloud.server.sys.module.spider.service.SpiderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;

@Slf4j
@Service
@AllArgsConstructor
public class SpiderServiceImpl implements SpiderService {


    @Override
    public void spider1() {
        Spider.create(new PageProcessor1())
                .addUrl("https://github.com/code4craft")
                .run();
    }
}
