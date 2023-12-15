package com.zifuji.cloud.server.business.module.book.controller.mo;

import com.zifuji.cloud.server.base.bean.BaseControllerMo;
import lombok.Data;

@Data
public class AddBookSectionMo extends BaseControllerMo {

    private Long bookId;

    private Integer sectionNo;

    private String sectionName;

    private String sectionContent;




}
