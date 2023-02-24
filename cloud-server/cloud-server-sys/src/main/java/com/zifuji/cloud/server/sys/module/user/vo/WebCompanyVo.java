package com.zifuji.cloud.server.sys.module.user.vo;

import com.zifuji.cloud.base.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebCompanyVo extends BaseVo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String companyName;

    private String deptCode;
}
