package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPageBookQo extends BaseControllerPageQo {

    private String bookName;

    private String bookAuth;

    private String bookType;
}
