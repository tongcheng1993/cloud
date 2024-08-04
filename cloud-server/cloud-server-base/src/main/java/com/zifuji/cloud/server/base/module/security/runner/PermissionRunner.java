package com.zifuji.cloud.server.base.module.security.runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.zifuji.cloud.server.base.module.feign.client.manage.manageUser.ManageUserFeignClient;
import com.zifuji.cloud.server.base.module.feign.client.manage.manageUser.mo.AddManagePermissionMo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@AllArgsConstructor
public class PermissionRunner implements ApplicationRunner {

	private ApplicationContext applicationContext;
	private ManageUserFeignClient manageUserFeignClient;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		List<String> preAuthorizeExpressions = new ArrayList<>();
		Map<String, Object> beans = applicationContext.getBeansWithAnnotation(RestController.class);
		for (Object bean : beans.values()) {
			Class<?> beanClass = bean.getClass();
			if (beanClass.getName().contains("CGLIB")) {
				Class<?> superClass = beanClass.getSuperclass();
				if (null != superClass && superClass != Object.class) {
					Method[] methods = superClass.getDeclaredMethods();
					for (Method method : methods) {
						if (method.isAnnotationPresent(PreAuthorize.class)) {
							PreAuthorize preAuthorize = method.getAnnotation(PreAuthorize.class);
							String expression = preAuthorize.value();
							expression = expression.substring(17);
							expression = expression.substring(0, expression.length() - 2);
							preAuthorizeExpressions.add(expression);
						}
					}
				}
			}
		}
		for (String str : preAuthorizeExpressions) {
			try {
				String[] strArr = str.split(":");
				AddManagePermissionMo addManagePermissionMo = new AddManagePermissionMo();
				addManagePermissionMo.setPerName(str);
				addManagePermissionMo.setCodeSys(strArr[0]);
				addManagePermissionMo.setCodeModule(strArr[1]);
				addManagePermissionMo.setCodeMethod(strArr[2]);
				addManagePermissionMo.setPerDescription(str);
				manageUserFeignClient.addManagePermissionInner(addManagePermissionMo);
			} catch (Exception e) {

			}

		}

	}

}
