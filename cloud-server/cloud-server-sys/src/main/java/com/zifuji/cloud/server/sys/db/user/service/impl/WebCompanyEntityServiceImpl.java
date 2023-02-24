package com.zifuji.cloud.server.sys.db.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.sys.db.user.entity.WebCompanyEntity;
import com.zifuji.cloud.server.sys.db.user.mapper.WebCompanyEntityMapper;
import com.zifuji.cloud.server.sys.db.user.service.WebCompanyEntityService;
import org.springframework.stereotype.Service;

@Service
public class WebCompanyEntityServiceImpl extends ServiceImpl<WebCompanyEntityMapper, WebCompanyEntity> implements WebCompanyEntityService {
}
