package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SaveCompanyInfoMo extends BaseMo {

    private String companyName;

    private String deptCode;
}
