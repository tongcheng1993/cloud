package com.zifuji.cloud.server.business.module.search.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.search.controller.vo.SearchVo;

public interface SearchService {
	
	IPage<SearchVo> search(String str);
	
}
