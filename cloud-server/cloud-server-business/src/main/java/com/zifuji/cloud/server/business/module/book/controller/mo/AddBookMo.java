package com.zifuji.cloud.server.business.module.book.controller.mo;

import com.zifuji.cloud.server.base.db.BaseMo;
import lombok.Data;

@Data
public class AddBookMo extends BaseMo {

    private String bookName;

    private String bookAuth;

    private String bookType;

    private Long bookImg;
}
