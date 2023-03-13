package com.zifuji.cloud.server.sys.db.useDb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.useDb.entity.SqlRecordEntity;
import com.zifuji.cloud.server.sys.db.useDb.mapper.SqlRecordEntityMapper;
import com.zifuji.cloud.server.sys.db.useDb.service.SqlRecordEntityService;
import org.springframework.stereotype.Service;

@Service
public class SqlRecordEntityServiceImpl extends ServiceImpl<SqlRecordEntityMapper, SqlRecordEntity> implements SqlRecordEntityService {
}
