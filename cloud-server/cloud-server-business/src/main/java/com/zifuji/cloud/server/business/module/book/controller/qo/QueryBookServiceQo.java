package com.zifuji.cloud.server.business.module.book.controller.qo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class QueryBookServiceQo {


    private Long bookId;

    private Long secId;


}
