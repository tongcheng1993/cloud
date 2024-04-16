package com.zifuji.cloud.server.sys.module.quartz.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QueryJobDetailQo;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.JobDetailVo;

public interface QuartzService {

   IPage<JobDetailVo> queryPageJobDetail(QueryJobDetailQo<JobDetailVo> queryJobDetailQo);
}
