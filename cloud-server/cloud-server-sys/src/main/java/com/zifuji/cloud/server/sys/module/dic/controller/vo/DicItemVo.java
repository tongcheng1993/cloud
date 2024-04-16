package com.zifuji.cloud.server.sys.module.dic.controller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.db.BaseControllerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DicItemVo extends BaseControllerVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dicId;
    // 字典编码
    private String itemCode = "";
    // 字典值
    private String itemValue = "";
    // 是否展示
    private Boolean showFlag = true;
    // 是否可以选择
    private Boolean checkFlag = true;
}
