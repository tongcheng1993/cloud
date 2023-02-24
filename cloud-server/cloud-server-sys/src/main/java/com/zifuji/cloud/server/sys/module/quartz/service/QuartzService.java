package com.zifuji.cloud.server.sys.module.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.quartz.mo.QuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.mo.UpdateCornMo;
import com.zifuji.cloud.server.sys.module.quartz.mo.UpdateStatusMo;
import com.zifuji.cloud.server.sys.module.quartz.qo.QuartzRecordPageQo;
import com.zifuji.cloud.server.sys.module.quartz.vo.QuartzRecordVo;
import org.quartz.SchedulerException;


public interface QuartzService {


    QuartzRecordVo saveQuartzRecord(QuartzRecordMo quartzRecordMo) throws Exception;

    Boolean delQuartzRecord(Long id) throws Exception;

    IPage<QuartzRecordVo> queryPageQuartzRecord(QuartzRecordPageQo quartzRecordPageQo);

    Boolean syncQuartzList();
}
