package com.zifuji.cloud.server.sys.module.email.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.email.bo.EmailRecordBo;
import com.zifuji.cloud.server.sys.module.email.bo.EmailTemplateBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailMapper {


    List<EmailRecordBo> selectListEmailRecord(@Param(Constants.WRAPPER) Wrapper<EmailRecordBo> ew);

    IPage<EmailRecordBo> selectPageEmailRecord(@Param(value = "page") Page<EmailRecordBo> page, @Param(Constants.WRAPPER) Wrapper<EmailRecordBo> ew);

    List<EmailTemplateBo> selectListEmailTemplate(@Param(Constants.WRAPPER) Wrapper<EmailTemplateBo> ew);

    IPage<EmailTemplateBo> selectPageEmailTemplate(@Param(value = "page") Page<EmailTemplateBo> page, @Param(Constants.WRAPPER) Wrapper<EmailTemplateBo> ew);
}
