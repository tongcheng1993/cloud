package com.zifuji.cloud.server.base.module.feign.client.sys.email;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "cloud-server-sys", contextId = "email", path = "/email")
public interface EmailFeignClient {







}
