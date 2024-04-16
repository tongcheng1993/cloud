package com.zifuji.cloud.server.sys.module.dic.controller.mo;

import com.zifuji.cloud.server.base.db.BaseControllerMo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResetDicItemMo extends BaseControllerMo {


    private String itemValue;

    // 是否展示
    private Boolean showFlag = true;
    // 是否可以选择
    private Boolean checkFlag = true;


}
