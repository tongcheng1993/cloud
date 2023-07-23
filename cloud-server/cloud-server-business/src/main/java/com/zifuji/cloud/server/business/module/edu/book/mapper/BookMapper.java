package com.zifuji.cloud.server.business.module.edu.book.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {

    List<Map<String, Object>> getShouYeTuiJian();

    List<Map<String, Object>> getZuiXinXiaoShuo();
}
