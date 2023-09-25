package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPageBookQo extends Page {

    private String bookName;

    private String bookAuth;

    private String bookType;
}
