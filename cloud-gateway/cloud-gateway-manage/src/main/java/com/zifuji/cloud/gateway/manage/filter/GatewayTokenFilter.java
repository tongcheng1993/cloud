package com.zifuji.cloud.gateway.manage.filter;

import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.WebsocketRoutingFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.BaseConstant;
import com.zifuji.cloud.base.bean.Result;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.gateway.manage.properties.WebIgnoreProperties;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class GatewayTokenFilter implements GlobalFilter, Ordered {

    private WebIgnoreProperties webIgnoreProperties;
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
        UserInfo userInfo = null;
        Map<String, Object> map = null;
        if (path.startsWith("/websocket/ws")) {
            MultiValueMap<String, String> multiValueMap = request.getQueryParams();
            String tc_token = multiValueMap.get("token").get(0);
            if (StrUtil.isBlank(tc_token)) {
                Result<String> result = new Result<String>();
                result.set400Mes("权限验证错误");
                return setResponseInfo(response, result);
            }
            String bo = stringRedisTemplate.opsForValue().get(tc_token);
            if (StrUtil.isBlank(bo)) {
                Result<String> result = new Result<String>();
                result.set400Mes("权限验证错误");
                return setResponseInfo(response, result);
            }
            stringRedisTemplate.opsForValue().set(tc_token, bo, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
            map = new HashMap<String, Object>();
            userInfo = JSONObject.parseObject(bo, UserInfo.class);
            map.put("userInfo", userInfo);
            String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
            ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("X-Access-Token", token);
            }).build();
            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        } else {
            // 获取当前请求中的token
            String tc_token = request.getHeaders().getFirst("Tc-Token");
            if (StrUtil.isBlank(tc_token)) {
                // 跳过不需要验证的路径
                List<String> ignoreUrl = webIgnoreProperties.getUri();
                for (String ignore : ignoreUrl) {
                    if (ignore.equals(path)
                            || (ignore.endsWith("**") && path.startsWith(ignore.substring(0, ignore.length() - 2)))) {
                        map = new HashMap<String, Object>();
                        userInfo = new UserInfo();
                        map.put("userInfo", userInfo);
                        String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
                        ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
                            httpHeaders.add("X-Access-Token", token);
                        }).build();
                        ServerWebExchange build = exchange.mutate().request(host).build();
                        return chain.filter(build);
                    }
                }
                Result<String> result = new Result<String>();
                result.set400Mes("权限验证错误");
                return setResponseInfo(response, result);
            }
            String bo = stringRedisTemplate.opsForValue().get(tc_token);
            if (StrUtil.isBlank(bo)) {
                Result<String> result = new Result<String>();
                result.set400Mes("权限验证错误");
                return setResponseInfo(response, result);
            }
            stringRedisTemplate.opsForValue().set(tc_token, bo, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
            map = new HashMap<String, Object>();
            userInfo = JSONObject.parseObject(bo, UserInfo.class);
            map.put("userInfo", userInfo);
            String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
            ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("X-Access-Token", token);
            }).build();
            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        }
    }


    private Mono<Void> setResponseInfo(ServerHttpResponse response, Object object) {
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] responseByte = new byte[0];
        try {
            responseByte = JSONObject.toJSONString(object).getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        DataBuffer buffer = response.bufferFactory().wrap(responseByte);
        return response.writeWith(Mono.just(buffer));
    }
}
