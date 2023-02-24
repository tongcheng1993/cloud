package com.zifuji.cloud.starter.web.util;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.base.bean.BaseOrderItem;

import cn.hutool.core.util.ObjectUtil;

public class MyBatisPlusUtil {
	
	
	public static  <T> QueryWrapper<T> orderWrapper(QueryWrapper<T> wrapper, List<BaseOrderItem> orders ){
		if (ObjectUtil.isNotEmpty(orders)) {
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getAsc()) {
					wrapper.orderByAsc(orders.get(i).getColumn());
				} else {
					wrapper.orderByDesc(orders.get(i).getColumn());
				}
			}
		}else{
			wrapper.orderByAsc("sort_num");
			wrapper.orderByDesc("create_time");
		}
		return wrapper;
	}

}
