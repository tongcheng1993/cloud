package com.zifuji.cloud.server.business.module.book.controller.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class CreateNewBookMo extends BaseControllerMo {

    private String bookName;

    private String bookAuth;

    private String bookType;

    private String bookImg;

}
