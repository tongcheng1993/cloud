package com.zifuji.cloud.server.sys.module.dic.controller.mo;

import com.zifuji.cloud.server.base.db.BaseMo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateDicMo extends BaseMo {


    private String dicName;

    private String dicDescription;
}
