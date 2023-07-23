package com.zifuji.cloud.server.business.module.edu.book.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class CreateNewSectionServiceMo {

    private Long bId;

    private String sName;

}
