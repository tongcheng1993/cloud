package com.zifuji.cloud.gateway.web.filter;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
        Map<String, Object> map = new HashMap<String, Object>();
        String bo = "";
<<<<<<< HEAD
        UserInfo userInfo = null;
        log.info(path);
        if (path.startsWith("/websocket/ws")) {
            MultiValueMap<String, String> multiValueMap = request.getQueryParams();
            String tc_token = multiValueMap.get("token").get(0);
            if (StrUtil.isBlank(tc_token)) {
                tc_token = request.getHeaders().getFirst("Tc-Token");
                if (StrUtil.isBlank(tc_token)) {
                    Result<String> result = new Result<String>();
                    result.set400Mes("权限验证错误");
                    return setResponseInfo(response, result);
                }
            }
            bo = stringRedisTemplate.opsForValue().get(tc_token);
            userInfo = JSONObject.parseObject(bo, UserInfo.class);
            if(ObjectUtil.isNull(userInfo)||StrUtil.isBlank(userInfo.getUserName())){
                Result<String> result = new Result<String>();
                result.set400Mes("权限验证错误");
                return setResponseInfo(response, result);
            }

=======
        if (path.startsWith("/websocket/ws")) {
            MultiValueMap<String, String> multiValueMap = request.getQueryParams();
            String tc_token = multiValueMap.get("token").get(0);
            bo = stringRedisTemplate.opsForValue().get(tc_token);
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        } else {
            // 获取当前请求中的token
            String tc_token = request.getHeaders().getFirst("Tc-Token");
            if(StrUtil.isBlank(tc_token)){
<<<<<<< HEAD
                bo = "";
=======

>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
            }else{
                bo = stringRedisTemplate.opsForValue().get(tc_token);
                if (StrUtil.isBlank(bo)) {
                    Result<String> result = new Result<String>();
                    result.set400Mes("权限验证错误");
                    return setResponseInfo(response, result);
                } else {
                    stringRedisTemplate.opsForValue().set(tc_token, bo, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
                }
<<<<<<< HEAD
            }
            userInfo = JSONObject.parseObject(bo, UserInfo.class);
            if(ObjectUtil.isNull(userInfo)){
                userInfo = new UserInfo();
            }
        }
        log.info("userInfo:{}",userInfo);
=======

            }
        }
        UserInfo userInfo = JSONObject.parseObject(bo, UserInfo.class);
        if(ObjectUtil.isNull(userInfo)){
            userInfo = new UserInfo();
        }
>>>>>>> f0666b324d0f19084264899903a51bf36f2b88df
        map.put("userInfo", userInfo);
        String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
        ServerHttpRequest host = request.mutate().headers(httpHeaders -> {
            httpHeaders.add("X-Access-Token", token);
        }).build();
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
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
