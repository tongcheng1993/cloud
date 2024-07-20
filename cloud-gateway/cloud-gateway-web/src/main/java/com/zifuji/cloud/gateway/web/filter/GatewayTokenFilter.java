package com.zifuji.cloud.gateway.web.filter;

import java.util.*;
import java.util.concurrent.TimeUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.gateway.base.util.ResponseUtil;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class GatewayTokenFilter implements GlobalFilter, Ordered {

	private StringRedisTemplate stringRedisTemplate;

	@Override
	public int getOrder() {
		return 0;
	}

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		ServerHttpResponse response = exchange.getResponse();

		// 获取当前请求路径
		String path = request.getURI().getPath();

		String from = request.getHeaders().getFirst("From");
		if (StrUtil.isNotBlank(from)) {
			// gateway 没有异常捕捉器 所以直接 return
			return ResponseUtil.setResponseInfo(response, Result.set30000Mes("验证对应身份信息失败"));
		}

		Map<String, Object> map = new HashMap<String, Object>();
		String tc_token = "";
		UserInfo userInfo = null;
		// 如果是websocket的请求
		if (path.startsWith("/websocket/ws")) {
			// url 地址中的参数 token 每个都必须有
			MultiValueMap<String, String> multiValueMap = request.getQueryParams();
			tc_token = multiValueMap.get("token").get(0);
			// 所有的websocket 都有token 没有token 就是身份验证有问题
			if (StrUtil.isBlank(tc_token)) {
				// gateway 没有异常捕捉器 所以直接 return
				return ResponseUtil.setResponseInfo(response, Result.set30000Mes("验证token失败"));
			}
			// token在redis中有对应的身份信息
			String bo = stringRedisTemplate.opsForValue().get(tc_token);
			if (StrUtil.isBlank(bo)) {
				// gateway 没有异常捕捉器 所以直接 return
				return ResponseUtil.setResponseInfo(response, Result.set30000Mes("验证对应身份信息失败"));
			}
			// 如果有token和 str 需要 重置token存续时间
			stringRedisTemplate.expire(tc_token, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
			userInfo = JSONObject.parseObject(bo, UserInfo.class);
			// 如果是其他的请求
		} else {
			// 获取当前请求中的token
			tc_token = request.getHeaders().getFirst("Tc-Token");
			// 如果没有token 就是游客
			if (StrUtil.isBlank(tc_token)) {
				// 设置游客身份信息
				userInfo = new UserInfo();
				userInfo.setTableId(-2L);
				userInfo.setUserName("游客");
				userInfo.setShortName("游客");
				userInfo.setType("web");
				List<String> roleCodeList = new ArrayList<>();
				userInfo.setRoleCodeList(roleCodeList);
				List<String> permissionCodeList = new ArrayList<>();
				userInfo.setPermissionCodeList(permissionCodeList);
			} else {
				// 每个token在redis中有对应的str
				String bo = stringRedisTemplate.opsForValue().get(tc_token);
				if (StrUtil.isBlank(bo)) {
					// gateway 没有异常捕捉器 所以直接 return
					return ResponseUtil.setResponseInfo(response, Result.set30000Mes("验证对应身份信息失败"));
				}
				// 如果有token和 str 需要 重置token存续时间
				stringRedisTemplate.expire(tc_token, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
				userInfo = JSONObject.parseObject(bo, UserInfo.class);
			}

		}
		if (ObjectUtil.isNull(userInfo) || ObjectUtil.isNull(userInfo.getTableId())) {
			return ResponseUtil.setResponseInfo(response, Result.set30000Mes("验证对应身份信息失败"));
		}
		map.put("userInfo", userInfo);
		// 网关通过后 在请求中增加 内部token
		String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
		ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
			httpHeaders.add("X-Access-Token", token);
		}).build();
		ServerWebExchange build = exchange.mutate().request(host).build();
		return chain.filter(build);
	}

}
