package com.zifuji.cloud.server.sys.module.area.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.base.db.mapper.MyBaseMapper;
import com.zifuji.cloud.server.sys.module.area.bo.AreaBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AreaMapper extends MyBaseMapper {

	List<AreaBo> selectListArea(@Param(Constants.WRAPPER) Wrapper<AreaBo> ew);

	IPage<AreaBo> selectPageArea(@Param(value = "page") Page<AreaBo> page,
			@Param(Constants.WRAPPER) Wrapper<AreaBo> ew);

}
