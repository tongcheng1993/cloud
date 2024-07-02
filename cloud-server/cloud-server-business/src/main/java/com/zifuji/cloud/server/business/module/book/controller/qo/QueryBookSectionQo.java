package com.zifuji.cloud.server.business.module.book.controller.qo;

import com.zifuji.cloud.server.base.db.BaseControllerQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QueryBookSectionQo<T> extends BaseControllerQo<T> {


    private Long bookId;

    private String sectionName;


}
