package com.zifuji.cloud.server.sys.module.seq.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.seq.bo.SeqBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SeqMapper {

    List<SeqBo> selectListSeq(@Param(Constants.WRAPPER) Wrapper<SeqBo> ew);

    IPage<SeqBo> selectPageSeq(@Param(value = "page") Page<SeqBo> page, @Param(Constants.WRAPPER) Wrapper<SeqBo> ew);
}
