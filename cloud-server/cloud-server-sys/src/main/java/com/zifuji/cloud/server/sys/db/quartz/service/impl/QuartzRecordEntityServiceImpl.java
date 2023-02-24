package com.zifuji.cloud.server.sys.db.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.quartz.entity.QuartzRecordEntity;
import com.zifuji.cloud.server.sys.db.quartz.mapper.QuartzRecordEntityMapper;
import com.zifuji.cloud.server.sys.db.quartz.service.QuartzRecordEntityService;
import org.springframework.stereotype.Service;

@Service
public class QuartzRecordEntityServiceImpl extends ServiceImpl<QuartzRecordEntityMapper, QuartzRecordEntity> implements QuartzRecordEntityService {
}
