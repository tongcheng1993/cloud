package com.zifuji.cloud.server.business.module.book.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CreateNewBookVo extends BaseControllerVo {

    private String bookName;

    private String bookAuth;

    private String bookType;

    private String bookImg;

}
