package com.zifuji.cloud.server.sys.module.quartz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.quartz.controller.bo.QuartzRecordComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuartzMapper {

    IPage<QuartzRecordComponentMo> selectPageQuartzRecord(@Param(value = "page") Page<QuartzRecordComponentMo> page, @Param(Constants.WRAPPER) Wrapper<QuartzRecordComponentMo> ew);
}
