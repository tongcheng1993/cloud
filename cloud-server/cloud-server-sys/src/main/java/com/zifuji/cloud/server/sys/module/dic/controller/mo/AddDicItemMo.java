package com.zifuji.cloud.server.sys.module.dic.controller.mo;

import lombok.Data;

@Data
public class AddDicItemMo {

    private Long dicId;

    private String itemCode;

    private String itemValue;

    // 是否展示
    private Boolean showFlag = true;
    // 是否可以选择
    private Boolean checkFlag = true;

}
