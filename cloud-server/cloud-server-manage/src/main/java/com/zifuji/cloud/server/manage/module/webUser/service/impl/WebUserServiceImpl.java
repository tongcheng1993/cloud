package com.zifuji.cloud.server.manage.module.webUser.service.impl;

import org.springframework.stereotype.Service;

import com.zifuji.cloud.server.manage.module.webUser.mapper.WebUserMapper;
import com.zifuji.cloud.server.manage.module.webUser.service.WebUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WebUserServiceImpl implements WebUserService {

	private WebUserMapper webUserMapper;

}
