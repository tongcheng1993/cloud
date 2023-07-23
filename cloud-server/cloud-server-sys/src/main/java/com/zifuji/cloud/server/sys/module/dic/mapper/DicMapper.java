package com.zifuji.cloud.server.sys.module.dic.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.dic.bo.DicComponentMo;
import com.zifuji.cloud.server.sys.module.dic.bo.DicItemComponentMo;

@Mapper
public interface DicMapper {

	List<DicComponentMo> selectListDic(@Param(Constants.WRAPPER) Wrapper<DicComponentMo> ew);
	 
	IPage<DicComponentMo> selectPageDic(@Param(value = "page") Page<DicComponentMo> page,
                                        @Param(Constants.WRAPPER) Wrapper<DicComponentMo> ew);
	
	List<DicItemComponentMo> selectListDicItem(@Param(Constants.WRAPPER) Wrapper<DicItemComponentMo> ew);
	
	IPage<DicItemComponentMo> selectPageDicItem(@Param(value = "page") Page<DicItemComponentMo> page,
                                                @Param(Constants.WRAPPER) Wrapper<DicItemComponentMo> ew);
}
