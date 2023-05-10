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

        String tc_token = "";
        UserInfo userInfo = null;

        Map<String, Object> map = new HashMap<String, Object>();

        // 如果是websocket的请求
        if (path.startsWith("/sys/ws")) {
            // url 地址中的参数 token 每个都必须有
            MultiValueMap<String, String> multiValueMap = request.getQueryParams();
            tc_token = multiValueMap.get("token").get(0);
            if (StrUtil.isBlank(tc_token)) {
                // gateway 没有异常捕捉器 所以直接 return 400
                Result<String> result = new Result<String>();
                result.set400Mes("验证token失败");
                return setResponseInfo(response, result);
            }
            // token在redis中有对应的身份信息
            String bo = stringRedisTemplate.opsForValue().get(tc_token);
            if (StrUtil.isBlank(bo)) {
                // gateway 没有异常捕捉器 所以直接 return 400
                Result<String> result = new Result<String>();
                result.set400Mes("验证对应身份信息失败");
                return setResponseInfo(response, result);
            }
            // 如果有token和 str 需要 重置token存续时间
            stringRedisTemplate.opsForValue().set(tc_token, bo, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
            userInfo = JSONObject.parseObject(bo, UserInfo.class);
            // 如果是其他的请求
        } else {
            // 获取当前请求中的token
            tc_token = request.getHeaders().getFirst("Tc-Token");
            // 如果没有token  就是登录
            if (StrUtil.isBlank(tc_token)) {
                // 检查路径是不是放行的路径
                boolean pathFlag = false;
                List<String> ignoreUrl = webIgnoreProperties.getUri();
                for (String ignore : ignoreUrl) {
                    if (ignore.equals(path)
                            || ((ignore.endsWith("**") && path.startsWith(ignore.substring(0, ignore.length() - 2))))) {
                        pathFlag = true;
                    }
                }
                // 如果是登录按钮 给与游客身份
                if (pathFlag) {
                    userInfo = new UserInfo();
                    //  如果不是直接
                } else {
                    // gateway 没有异常捕捉器 所以直接 return 400
                    Result<String> result = new Result<String>();
                    result.set400Mes("验证对应身份信息失败");
                    return setResponseInfo(response, result);
                }
                // 如果有token
            }else{
                // token在redis中有对应的身份信息
                String bo = stringRedisTemplate.opsForValue().get(tc_token);
                if (StrUtil.isBlank(bo)) {
                    // gateway 没有异常捕捉器 所以直接 return 400
                    Result<String> result = new Result<String>();
                    result.set400Mes("验证对应身份信息失败");
                    return setResponseInfo(response, result);
                }
                stringRedisTemplate.opsForValue().set(tc_token, bo, 1000 * 60 * 30, TimeUnit.MILLISECONDS);
                userInfo = JSONObject.parseObject(bo, UserInfo.class);
            }
        }
        if (ObjectUtil.isNull(userInfo) || StrUtil.isBlank(userInfo.getUserName())) {
            // gateway 没有异常捕捉器 所以直接 return 400
            Result<String> result = new Result<String>();
            result.set400Mes("验证身份信息格式化失败");
            return setResponseInfo(response, result);
        }

        log.info("tc_token:{}", tc_token);
        log.info("userInfo:{}", userInfo);


        map.put("userInfo", userInfo);
        // 网关通过后 在请求中增加 内部token
        String token = JWTUtil.createToken(map, BaseConstant.KEY.getBytes());
        log.info("token:{}", token);
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
