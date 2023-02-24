package com.zifuji.cloud.server.sys.module.quartz.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.quartz.bo.QuartzRecordBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QuartzMapper {

    IPage<QuartzRecordBo> selectPageQuartzRecord(@Param(value = "page") Page<QuartzRecordBo> page, @Param(Constants.WRAPPER) Wrapper<QuartzRecordBo> ew);
}
