package com.zifuji.cloud.server.base.util;

import cn.hutool.core.util.ObjectUtil;
import com.zifuji.cloud.base.bean.UserInfo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

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
        Object object = authentication.getDetails();
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
        userInfo.setUserName("定时任务角色");
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
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(userInfo.getId(), userInfo.getUserName(), list);
        usernamePasswordAuthenticationToken.setDetails(userInfo);
        securityContext.setAuthentication(usernamePasswordAuthenticationToken);


    }

}