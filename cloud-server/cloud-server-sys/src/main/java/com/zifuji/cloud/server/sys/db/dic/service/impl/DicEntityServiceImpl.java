package com.zifuji.cloud.server.sys.db.dic.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.dic.entity.DicEntity;
import com.zifuji.cloud.server.sys.db.dic.mapper.DicEntityMapper;
import com.zifuji.cloud.server.sys.db.dic.service.DicEntityService;
@Service
public class DicEntityServiceImpl extends ServiceImpl<DicEntityMapper, DicEntity> implements DicEntityService {

}
