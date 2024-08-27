package com.zifuji.cloud.server.business.module.search.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.book.controller.mo.AddBookMo;
import com.zifuji.cloud.server.business.module.book.controller.vo.BookVo;
import com.zifuji.cloud.server.business.module.search.controller.qo.SearchQo;
import com.zifuji.cloud.server.business.module.search.controller.vo.SearchVo;
import com.zifuji.cloud.server.business.module.search.service.SearchService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "搜索控制器")
@RestController
@RequestMapping(value = "/search")
@AllArgsConstructor
public class SearchController {

	private SearchService searchService;

	@ApiOperation(value = "")
	@PostMapping(value = "/search")
	public Result<IPage<SearchVo>> search(@RequestBody @Valid SearchQo searchQo) {
		return Result.setObj(searchService.search(searchQo));
	}

}
