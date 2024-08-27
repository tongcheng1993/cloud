package com.zifuji.cloud.server.business.module.website.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.server.business.module.website.controller.mo.AddWebsiteMo;
import com.zifuji.cloud.server.business.module.website.controller.mo.UpdateWebsiteMo;
import com.zifuji.cloud.server.business.module.website.controller.qo.QueryWebsiteQo;
import com.zifuji.cloud.server.business.module.website.controller.vo.WebsiteVo;

public interface WebsiteService {

	WebsiteVo addWebsite(AddWebsiteMo addWebsiteMo);

	Boolean delWebsite(Long tableId);

	WebsiteVo updateWebsite(UpdateWebsiteMo updateWebsiteMo);

	List<WebsiteVo> queryListWebsite(QueryWebsiteQo queryWebsiteQo);

	IPage<WebsiteVo> queryPageWebsite(QueryWebsiteQo queryWebsiteQo);

	WebsiteVo getWebsiteById(Long tableId);
}
