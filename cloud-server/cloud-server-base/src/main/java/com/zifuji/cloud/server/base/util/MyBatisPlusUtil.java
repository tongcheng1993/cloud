package com.zifuji.cloud.server.base.util;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zifuji.cloud.server.base.bean.BaseOrderItem;

import java.util.List;

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
