package com.zifuji.cloud.server.base.util;

import cn.hutool.core.util.ObjectUtil;

import com.zifuji.cloud.base.bean.Exception20000;
import com.zifuji.cloud.base.bean.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SecurityUtil {

	public static UserInfo getUserDetails() {
		boolean flag = false;
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (ObjectUtil.isNotNull(securityContext)) {
			Authentication authentication = securityContext.getAuthentication();
			if (ObjectUtil.isNotNull(authentication)) {
				Object object = authentication.getDetails();
				if (ObjectUtil.isNotNull(object)) {
					if (object instanceof UserInfo) {
						return (UserInfo) object;
					} else {
						flag = true;
					}
				} else {
					flag = true;
				}
			} else {
				flag = true;
			}
		} else {
			flag = true;
		}
		if (flag) {
			UserInfo userInfo = new UserInfo();
			userInfo.setTableId(-1L);
			return userInfo;
		} else {
			throw new Exception20000("请从正确的入口进入系统");
		}
	}

	public static void setUserDetails(UserInfo userInfo) {
		List<GrantedAuthority> list = new ArrayList<>();
		if (ObjectUtil.isNotEmpty(userInfo.getRoleCodeList())) {
			for (String role : userInfo.getRoleCodeList()) {
				list.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
		}
		if (ObjectUtil.isNotEmpty(userInfo.getPermissionCodeList())) {
			for (String permission : userInfo.getPermissionCodeList()) {
				list.add(new SimpleGrantedAuthority(permission));
			}
		}
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userInfo.getTableId(), userInfo.getUserName(), list);
		usernamePasswordAuthenticationToken.setDetails(userInfo);
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

	}

}
