package com.zifuji.cloud.server.base.feign.sys;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sys", contextId = "email", path = "/email")
public interface EmailFeignClient {
}
