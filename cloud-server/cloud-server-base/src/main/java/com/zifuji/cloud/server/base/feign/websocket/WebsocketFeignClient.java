package com.zifuji.cloud.server.base.feign.websocket;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "websocket", contextId = "websocket", path = "/websocket")
public interface WebsocketFeignClient {

    @PostMapping(value = "/sendWsMessage")
    Result<Boolean> sendWsMessage(@RequestBody JSONObject jsonObject);
}
