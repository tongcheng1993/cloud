package com.zifuji.cloud.server.manage.module.webUser.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.module.feign.client.business.webUser.WebUserFeignClient;
import com.zifuji.cloud.server.base.module.feign.client.business.webUser.qo.QueryWebUserQo;
import com.zifuji.cloud.server.base.module.feign.client.business.webUser.vo.WebUserVo;
import com.zifuji.cloud.server.manage.module.webUser.service.WebUserService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;

@Api(tags = "用户控制器")
@RestController
@RequestMapping(value = "/webUser")
@AllArgsConstructor
public class WebUserController {

	private WebUserFeignClient webUserFeignClient;

	private WebUserService webUserService;

	@PostMapping(value = "/queryPageWebUser")
	public Result<IPage<WebUserVo>> queryPageWebUser(@RequestBody @Valid QueryWebUserQo queryWebUserQo) {
		return webUserFeignClient.queryPageWebUser(queryWebUserQo);
	}

}
