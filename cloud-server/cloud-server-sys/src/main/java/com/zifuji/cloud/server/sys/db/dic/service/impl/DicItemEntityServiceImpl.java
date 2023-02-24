package com.zifuji.cloud.server.sys.db.dic.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.dic.entity.DicItemEntity;
import com.zifuji.cloud.server.sys.db.dic.mapper.DicItemEntityMapper;
import com.zifuji.cloud.server.sys.db.dic.service.DicItemEntityService;

@Service
public class DicItemEntityServiceImpl extends ServiceImpl<DicItemEntityMapper, DicItemEntity>implements DicItemEntityService{

}
