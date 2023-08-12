package com.zifuji.cloud.server.sys.module.quartz.job;


import com.zifuji.cloud.server.base.feign.business.BookFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component
@AllArgsConstructor
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

    private BookFeignClient bookFeignClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        bookFeignClient.job();
        log.info(context.getTrigger().getKey() + " Job 执行时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
