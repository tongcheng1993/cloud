package com.zifuji.cloud.server.base.feign.business;

import com.zifuji.cloud.base.bean.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cloud-server-business", contextId = "score", path = "/score")
public interface ScoreFeignClient {


    @GetMapping(value = "/registerScore")
    Result<Boolean> registerScore(@RequestParam Long createById);
}
