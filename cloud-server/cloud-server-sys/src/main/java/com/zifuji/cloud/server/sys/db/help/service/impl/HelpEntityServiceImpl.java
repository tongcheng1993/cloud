package com.zifuji.cloud.server.sys.db.help.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.help.entity.HelpEntity;
import com.zifuji.cloud.server.sys.db.help.mapper.HelpEntityMapper;
import com.zifuji.cloud.server.sys.db.help.service.HelpEntityService;
import org.springframework.stereotype.Service;

@Service
public class HelpEntityServiceImpl extends ServiceImpl<HelpEntityMapper, HelpEntity> implements HelpEntityService {
}
