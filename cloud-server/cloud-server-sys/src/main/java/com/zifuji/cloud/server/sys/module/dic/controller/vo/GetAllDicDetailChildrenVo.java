package com.zifuji.cloud.server.sys.module.dic.controller.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class GetAllDicDetailChildrenVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String itemCode;

    private String itemValue;

    private Boolean showFlag;

    private Boolean checkFlag;

}
