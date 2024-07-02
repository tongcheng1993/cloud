package com.zifuji.cloud.server.business.module.book.controller.mo;

import com.zifuji.cloud.server.base.db.BaseControllerMo;
import lombok.Data;

@Data
public class AddBookMo extends BaseControllerMo {

    private String bookName;

    private String bookAuth;

    private String bookType;

    private Long bookImg;
}
