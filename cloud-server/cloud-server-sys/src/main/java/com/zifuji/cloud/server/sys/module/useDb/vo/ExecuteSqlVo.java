package com.zifuji.cloud.server.sys.module.useDb.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ExecuteSqlVo  {

    private Integer sqlCount;

    private String type;

    private IPage<Map<String, Object>> page;
}
