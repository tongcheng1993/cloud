package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryBookQo<T> extends BaseControllerQo<T> {

    private String bookName;

    private String bookAuth;

    private String bookType;
}
