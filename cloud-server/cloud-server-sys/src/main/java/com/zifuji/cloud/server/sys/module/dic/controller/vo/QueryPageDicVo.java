package com.zifuji.cloud.server.sys.module.dic.controller.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(description = "")
public class QueryPageDicVo {


    private String id;

    // 字典名字
    private String dicName = "";
    // 字典编码
    private String dicCode = "";
    // 描述
    private String dicDescription = "";

}
