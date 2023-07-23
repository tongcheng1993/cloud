package com.zifuji.cloud.server.sys.module.email.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zifuji.cloud.server.sys.module.email.bo.EmailRecordComponentMo;
import com.zifuji.cloud.server.sys.module.email.bo.EmailTemplateComponentMo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmailMapper {


    List<EmailRecordComponentMo> selectListEmailRecord(@Param(Constants.WRAPPER) Wrapper<EmailRecordComponentMo> ew);

    IPage<EmailRecordComponentMo> selectPageEmailRecord(@Param(value = "page") Page<EmailRecordComponentMo> page, @Param(Constants.WRAPPER) Wrapper<EmailRecordComponentMo> ew);

    List<EmailTemplateComponentMo> selectListEmailTemplate(@Param(Constants.WRAPPER) Wrapper<EmailTemplateComponentMo> ew);

    IPage<EmailTemplateComponentMo> selectPageEmailTemplate(@Param(value = "page") Page<EmailTemplateComponentMo> page, @Param(Constants.WRAPPER) Wrapper<EmailTemplateComponentMo> ew);
}
