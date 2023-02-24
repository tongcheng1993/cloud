package com.zifuji.cloud.starter.web.object;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.zifuji.cloud.base.bean.UserInfo;

import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtil {
    public static UserInfo getUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (ObjectUtil.isNull(authentication)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(0L);
            return userInfo;
        }
        Object object = authentication.getPrincipal();
        if (ObjectUtil.isNull(object)) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(0L);
            return userInfo;
        } else {
            return (UserInfo) object;
        }
    }

    public static void setUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        userInfo.setName("定时任务角色");
        userInfo.setRoleList(new ArrayList<>());
        userInfo.setPermissionList(new ArrayList<>());
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
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userInfo, "token", list));


    }

}
