package com.zifuji.cloud.server.sys.module.dic.controller.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DicVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String dicName;

    private String dicCode;

    private List<DicItemVo> children;


}
