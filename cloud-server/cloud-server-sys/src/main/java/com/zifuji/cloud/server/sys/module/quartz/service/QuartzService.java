package com.zifuji.cloud.server.sys.module.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.quartz.mo.QuartzRecordControllerMo;
import com.zifuji.cloud.server.sys.module.quartz.qo.QuartzRecordPageQo;
import com.zifuji.cloud.server.sys.module.quartz.vo.QuartzRecordControllerVo;


public interface QuartzService {


    QuartzRecordControllerVo saveQuartzRecord(QuartzRecordControllerMo quartzRecordMo) throws Exception;

    Boolean delQuartzRecord(Long id) throws Exception;

    IPage<QuartzRecordControllerVo> queryPageQuartzRecord(QuartzRecordPageQo quartzRecordPageQo);

    Boolean syncQuartzList();
}
