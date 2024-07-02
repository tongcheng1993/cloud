package com.zifuji.cloud.server.sys.module.seq.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveSeqControllerMo  {
    // 描述
    private String description;
    // 流水号更新规则   日流水 月流水 年流水 永久流水
    private String rebornType;
    // 补全的标记
    private String supplementFlag;
    // 完整的位数
    private Integer supplementNum;
    // 前缀
    private String prefix;
    // 后缀
    private String suffix;
    // redis中code
    private String code;
}
