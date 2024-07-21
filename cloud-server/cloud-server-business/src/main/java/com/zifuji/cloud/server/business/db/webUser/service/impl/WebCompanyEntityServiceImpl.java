package com.zifuji.cloud.server.business.db.webUser.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zifuji.cloud.server.business.db.webUser.entity.WebCompanyEntity;
import com.zifuji.cloud.server.business.db.webUser.mapper.WebCompanyEntityMapper;
import com.zifuji.cloud.server.business.db.webUser.service.WebCompanyEntityService;

import org.springframework.stereotype.Service;

@Service
public class WebCompanyEntityServiceImpl extends ServiceImpl<WebCompanyEntityMapper, WebCompanyEntity> implements WebCompanyEntityService {
}
