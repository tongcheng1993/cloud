package com.zifuji.cloud.server.base.module.feign.client.websocket.websocket;

import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.server.base.module.feign.client.websocket.websocket.mo.SendWsMessageMo;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cloud-server-websocket", contextId = "websocket", path = "/websocket")
public interface WebsocketFeignClient {

	@PostMapping(value = "/sendWsOneMessage")
	Result<Boolean> sendWsOneMessage(@RequestBody @Valid SendWsMessageMo sendWsMessageMo);
}
