package com.zifuji.cloud.server.sys.db.seq.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.seq.entity.SeqEntity;
import com.zifuji.cloud.server.sys.db.seq.mapper.SeqEntityMapper;
import com.zifuji.cloud.server.sys.db.seq.service.SeqEntityService;

@Service
public class SeqEntityServiceImpl extends ServiceImpl<SeqEntityMapper, SeqEntity> implements SeqEntityService{

}
