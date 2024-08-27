package com.zifuji.cloud.server.business.module.website.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.business.module.website.controller.mo.AddWebsiteMo;
import com.zifuji.cloud.server.business.module.website.controller.mo.UpdateWebsiteMo;
import com.zifuji.cloud.server.business.module.website.controller.qo.QueryWebsiteQo;
import com.zifuji.cloud.server.business.module.website.controller.vo.WebsiteVo;
import com.zifuji.cloud.server.business.module.website.service.WebsiteService;

public class WebsiteController {

	private WebsiteService websiteService;

	public Result<WebsiteVo> addWebsite(AddWebsiteMo addWebsiteMo) {
		return Result.setObj(websiteService.addWebsite(addWebsiteMo));
	}

	public Result<Boolean> delWebsite(Long tableId) {
		return Result.setObj(websiteService.delWebsite(tableId));
	}

	public Result<WebsiteVo> updateWebsite(UpdateWebsiteMo updateWebsiteMo) {
		return Result.setObj(websiteService.updateWebsite(updateWebsiteMo));
	}

	public Result<List<WebsiteVo>> queryListWebsite(QueryWebsiteQo queryWebsiteQo) {
		return Result.setObj(websiteService.queryListWebsite(queryWebsiteQo));
	}

	public Result<IPage<WebsiteVo>> queryPageWebsite(QueryWebsiteQo queryWebsiteQo) {
		return Result.setObj(websiteService.queryPageWebsite(queryWebsiteQo));
	}

	public Result<WebsiteVo> getWebsiteById(Long tableId) {
		return Result.setObj(websiteService.getWebsiteById(tableId));
	}

}
