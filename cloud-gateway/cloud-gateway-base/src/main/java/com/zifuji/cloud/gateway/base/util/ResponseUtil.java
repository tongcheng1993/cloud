package com.zifuji.cloud.gateway.base.util;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;

import com.alibaba.fastjson.JSONObject;

import reactor.core.publisher.Mono;

public class ResponseUtil {

	public static Mono<Void> setResponseInfo(ServerHttpResponse response, Object object) {
		response.setStatusCode(HttpStatus.OK);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		byte[] responseByte = new byte[0];
		responseByte = JSONObject.toJSONString(object).getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(responseByte);
		return response.writeWith(Mono.just(buffer));
	}

}
