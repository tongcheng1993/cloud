package com.zifuji.cloud.server.sys.module.quartz.job;


import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWTUtil;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.feign.business.BookFeignClient;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@Component
@AllArgsConstructor
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

    private BookFeignClient bookFeignClient;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Map<String, Object> map = new HashMap<String, Object>();
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1624026565227151361L);
        userInfo.setUserName("登录" + DateUtil.now());
        userInfo.setType("web");
        List<String> roleCodeList = new ArrayList<>();
        roleCodeList.add(BaseConstant.ROLE_VISIT);
        userInfo.setRoleCodeList(roleCodeList);
        List<String> permissionCodeList = new ArrayList<>();
        userInfo.setPermissionCodeList(permissionCodeList);
        map.put("userInfo", userInfo);
        String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
        bookFeignClient.job(token);
        log.info(context.getTrigger().getKey() + " Job 执行时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
