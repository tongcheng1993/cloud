package com.zifuji.cloud.server.sys.module.dic.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.dic.bo.DicBo;
import com.zifuji.cloud.server.sys.module.dic.bo.DicItemBo;

@Mapper
public interface DicMapper {

	List<DicBo> selectListDic(@Param(Constants.WRAPPER) Wrapper<DicBo> ew);
	 
	IPage<DicBo> selectPageDic(@Param(value = "page") Page<DicBo> page,
	@Param(Constants.WRAPPER) Wrapper<DicBo> ew);
	
	List<DicItemBo> selectListDicItem(@Param(Constants.WRAPPER) Wrapper<DicItemBo> ew);
	
	IPage<DicItemBo> selectPageDicItem(@Param(value = "page") Page<DicItemBo> page,
			@Param(Constants.WRAPPER) Wrapper<DicItemBo> ew);
}
