package com.zifuji.cloud.server.sys.module.dic.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "")
public class QueryPageDicQo extends Page {

    private String dicName;

    private String dicCode;


}
