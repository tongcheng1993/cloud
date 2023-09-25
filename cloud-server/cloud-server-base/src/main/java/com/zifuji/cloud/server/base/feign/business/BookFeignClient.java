package com.zifuji.cloud.server.base.feign.business;

import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cloud-server-business", contextId = "book", path = "/book")
public interface BookFeignClient {

    @GetMapping(value = "/job", headers = "from=Y")
    Result<Boolean> job(@RequestHeader(name = "X-Access-Token", required = true) String token);
}
