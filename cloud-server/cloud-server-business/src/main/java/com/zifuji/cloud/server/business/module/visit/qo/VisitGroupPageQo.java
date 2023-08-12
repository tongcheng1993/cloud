package com.zifuji.cloud.server.business.module.visit.qo;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class VisitGroupPageQo extends BaseControllerPageQo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String name;
}
