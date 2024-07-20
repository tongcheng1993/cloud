package com.zifuji.cloud.server.business.module.search.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.search.controller.vo.SearchVo;
import com.zifuji.cloud.server.business.module.search.service.SearchService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

	@Override
	public IPage<SearchVo> search(String str) {
		log.info(str);
		return null;
	}

}
