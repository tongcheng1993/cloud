package com.zifuji.cloud.feign.common.sys;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "sys", contextId = "email", path = "/email")
public interface EmailFeignClient {
}
