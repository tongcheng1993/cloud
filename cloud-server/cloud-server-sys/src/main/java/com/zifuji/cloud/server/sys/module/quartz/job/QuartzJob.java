package com.zifuji.cloud.server.sys.module.quartz.job;


import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.starter.web.object.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Slf4j
@Component
@AllArgsConstructor
public class QuartzJob extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info(context.getTrigger().getKey() + " Job 执行时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        SecurityUtil.setUserDetails();
        UserInfo userInfo = SecurityUtil.getUserDetails();
        log.info("userInfo:{}", userInfo);
    }
}
