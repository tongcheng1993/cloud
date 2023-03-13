package com.zifuji.cloud.server.sys.db.data.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.data.entity.EmailEntity;
import com.zifuji.cloud.server.sys.db.data.mapper.EmailEntityMapper;
import com.zifuji.cloud.server.sys.db.data.service.EmailEntityService;
import org.springframework.stereotype.Service;

@Service
public class EmailEntityServiceImpl extends ServiceImpl<EmailEntityMapper, EmailEntity> implements EmailEntityService {
}
