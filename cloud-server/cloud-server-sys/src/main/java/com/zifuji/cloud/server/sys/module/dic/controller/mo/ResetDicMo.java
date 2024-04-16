package com.zifuji.cloud.server.sys.module.dic.controller.mo;

import com.zifuji.cloud.server.base.db.BaseControllerMo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ResetDicMo extends BaseControllerMo {


    private String dicName;

    private String dicDescription;
}
