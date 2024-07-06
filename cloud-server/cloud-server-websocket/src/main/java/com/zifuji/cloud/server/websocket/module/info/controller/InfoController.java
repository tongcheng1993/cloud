package com.zifuji.cloud.server.websocket.module.info.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.websocket.module.info.service.InfoService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(value = "消息控制器")
@Slf4j
@RestController
@RequestMapping(value = "/info")
@AllArgsConstructor
public class InfoController {

	private InfoService infoService;

	@PostMapping("/getInfo")
	public Result<String> getInfo() {
		return Result.setObj(infoService.getInfo());
	}

}
