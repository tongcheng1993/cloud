package com.zifuji.cloud.starter.web.object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.alibaba.fastjson.JSONObject;
import com.zifuji.cloud.base.bean.UserInfo;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenFilter extends BasicAuthenticationFilter {

    public TokenFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info(request.getRequestURI());
        // 每个请求都带有token
        if (request.getRequestURI().startsWith("/ws")) {
            request.getSession().setMaxInactiveInterval(-1);
        }
        String token = request.getHeader("X-Access-Token");
        if (StrUtil.isBlank(token)) {
            chain.doFilter(request, response);
        }
        JWT jwt = JWTUtil.parseToken(token);
        Object object = jwt.getPayload("userInfo");
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(object);
        UserInfo userInfo = JSONObject.toJavaObject(jsonObject, UserInfo.class);
        log.info("userInfo:{}",userInfo);
        List<GrantedAuthority> list = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(userInfo.getRoleList())) {
            for (String role : userInfo.getRoleList()) {
                list.add(new SimpleGrantedAuthority("ROLE_" + role));
            }
        }
        if (ObjectUtil.isNotEmpty(userInfo.getPermissionList())) {
            for (String permiString : userInfo.getPermissionList()) {
                list.add(new SimpleGrantedAuthority(permiString));
            }
        }
        UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(userInfo, token, list);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authResult);
        chain.doFilter(request, response);
    }
}
