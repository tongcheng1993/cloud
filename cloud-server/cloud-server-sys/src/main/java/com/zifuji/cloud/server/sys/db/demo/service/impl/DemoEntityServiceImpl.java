package com.zifuji.cloud.server.sys.db.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.demo.entity.DemoEntity;
import com.zifuji.cloud.server.sys.db.demo.mapper.DemoEntityMapper;
import com.zifuji.cloud.server.sys.db.demo.service.DemoEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DemoEntityServiceImpl extends ServiceImpl<DemoEntityMapper, DemoEntity> implements DemoEntityService {
}
