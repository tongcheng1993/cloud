package com.zifuji.cloud.server.sys.module.quartz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.AddQuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.controller.mo.ResetQuartzRecordMo;
import com.zifuji.cloud.server.sys.module.quartz.controller.qo.QuartzRecordQo;
import com.zifuji.cloud.server.sys.module.quartz.controller.vo.QuartzRecordVo;


public interface QuartzService {


    QuartzRecordVo addQuartzRecord(AddQuartzRecordMo quartzRecordMo);

    QuartzRecordVo resetQuartzRecord(ResetQuartzRecordMo quartzRecordMo);

    Boolean delQuartzRecord(Long id) ;

    IPage<QuartzRecordVo> queryPageQuartzRecord(QuartzRecordQo quartzRecordQo);

    Boolean syncQuartzList();
}
