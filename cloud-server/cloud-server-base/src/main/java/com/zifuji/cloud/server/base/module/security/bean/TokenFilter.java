package com.zifuji.cloud.server.base.module.security.bean;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.module.exception.bean.Exception30000;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class TokenFilter extends BasicAuthenticationFilter {

    public TokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // ws请求
        if (request.getRequestURI().startsWith("/ws")) {
            // 设置session时间无限
            request.getSession().setMaxInactiveInterval(-1);
        }
        // 每个请求都带有token
        String token = request.getHeader("X-Access-Token");
        // 没有token的是身份验证有问题，报400 异常
        if (StrUtil.isBlank(token)) {
            throw new Exception30000("请从正确的系统入口登录");
        }
        // 有token的 转成 jwt 对象
        JWT jwt = JWTUtil.parseToken(token);
        // 无法转化成功的是身份验证有问题 报400 异常
        if (ObjectUtil.isNull(jwt)) {
            throw new Exception30000("验证jwt失败");
        }
        Object object = jwt.getPayload("userInfo");
        // 无法获得内容的是身份验证有问题 报400 异常
        if (ObjectUtil.isNull(object)) {
            throw new Exception30000("验证Payload失败");
        }
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
        // 无法格式化的是身份验证有问题 报400 异常
        if (ObjectUtil.isNull(jsonObject)) {
            throw new Exception30000("验证JSONObject失败");
        }
        UserInfo userInfo = JSONObject.toJavaObject(jsonObject, UserInfo.class);
        // 无法格式化的是身份验证有问题 报400 异常
        if (ObjectUtil.isNull(userInfo)) {
            throw new Exception30000("验证userInfo格式失败");
        }
        // 当前用户的角色信息和权限信息
        SecurityUtil.setUserDetails(userInfo);

        log.info("当前请求：" + request.getRequestURI());
        log.info("当前登陆人：" + JSONObject.toJSONString(userInfo));
        // 放行请求
        chain.doFilter(request, response);
    }
}
