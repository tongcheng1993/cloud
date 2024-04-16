package com.zifuji.cloud.server.base.module.feign.client.websocket.websocket;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cloud-server-websocket", contextId = "websocket", path = "/websocket")
public interface WebsocketFeignClient {

    @PostMapping(value = "/sendWsMessage")
    Result<Boolean> sendWsMessage(@RequestBody JSONObject jsonObject);
}
