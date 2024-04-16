package com.zifuji.cloud.server.base.module.mybatis.bean;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zifuji.cloud.base.bean.UserInfo;
import com.zifuji.cloud.server.base.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

@Slf4j
public class MetaHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {
		// 获取登录用户id
		UserInfo userInfo = SecurityUtil.getUserDetails();
		if (ObjectUtil.isNull(getFieldValByName("createBy", metaObject))) {
			this.setFieldValByName("createBy", userInfo.getId(), metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("createTime", metaObject))) {
			this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("updateBy", metaObject))) {
			this.setFieldValByName("updateBy", userInfo.getId(), metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("updateTime", metaObject))) {
			this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("sortNum", metaObject))) {
			this.setFieldValByName("sortNum", 0, metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("delFlag", metaObject))) {
			this.setFieldValByName("delFlag", false, metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		// 获取登录用户id
		UserInfo userInfo = SecurityUtil.getUserDetails();
		if (ObjectUtil.isNull(getFieldValByName("updateBy", metaObject))) {
			this.setFieldValByName("updateBy", userInfo.getId(), metaObject);
		}
		if (ObjectUtil.isNull(getFieldValByName("updateTime", metaObject))) {
			this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		}

	}

}
