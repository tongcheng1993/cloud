package com.zifuji.cloud.server.base.feign.business;

import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "cloud-server-business", contextId = "book", path = "/book")
public interface BookFeignClient {

    @GetMapping(value = "/job")
    Result<Boolean> job();
}
