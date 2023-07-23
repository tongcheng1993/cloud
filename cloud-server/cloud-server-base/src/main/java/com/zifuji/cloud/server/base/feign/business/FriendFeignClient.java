package com.zifuji.cloud.server.base.feign.business;

import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-server-business", contextId = "friend", path = "/friend")
public interface FriendFeignClient {

    @GetMapping(value = "/registerFriend")
    Result<Boolean> registerFriend(@RequestParam Long createById);


}
