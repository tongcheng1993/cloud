package com.zifuji.cloud.server.sys.module.seq.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.seq.bo.SeqComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeqMapper {

    List<SeqComponentMo> selectListSeq(@Param(Constants.WRAPPER) Wrapper<SeqComponentMo> ew);

    IPage<SeqComponentMo> selectPageSeq(@Param(value = "page") Page<SeqComponentMo> page, @Param(Constants.WRAPPER) Wrapper<SeqComponentMo> ew);
}
