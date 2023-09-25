package com.zifuji.cloud.server.sys.module.dic.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AddDicMo {

    private String dicName;
    @NotBlank(message = "")
    private String dicCode;

    private String dicDescription;


}
