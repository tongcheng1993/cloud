package com.zifuji.cloud.server.sys.module.area.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.area.bo.AreaComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper {

	List<AreaComponentMo> selectListArea(@Param(Constants.WRAPPER) Wrapper<AreaComponentMo> ew);

	IPage<AreaComponentMo> selectPageArea(@Param(value = "page") Page<AreaComponentMo> page,
                                          @Param(Constants.WRAPPER) Wrapper<AreaComponentMo> ew);

}
