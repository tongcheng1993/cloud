package com.zifuji.cloud.server.base.module.feign.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zifuji.cloud.base.bean.Exception40000;

import cn.hutool.core.util.StrUtil;

@Aspect
@Component
public class InnerAspect {

	@Pointcut("@annotation(com.zifuji.cloud.server.base.module.feign.bean.Inner)")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void beforeAdvice() {

		// 获取HttpServletRequest对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 获取请求头信息
		String headerValue = request.getHeader("From");

		if (StrUtil.isNotBlank(headerValue) && StrUtil.equals("Y", headerValue)) {

		} else {
			throw new Exception40000("内部接口无法访问");
		}
	}
}
