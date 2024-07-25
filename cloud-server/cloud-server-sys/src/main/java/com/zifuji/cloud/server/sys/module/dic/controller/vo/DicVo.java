package com.zifuji.cloud.server.sys.module.dic.controller.vo;

import com.zifuji.cloud.server.base.db.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
public class DicVo extends BaseVo {

    // 字典名字
    private String dicName = "";
    // 字典编码
    private String dicCode = "";
    // 描述
    private String dicDescription = "";

    private List<DicItemVo> dicItemVoList;

}
