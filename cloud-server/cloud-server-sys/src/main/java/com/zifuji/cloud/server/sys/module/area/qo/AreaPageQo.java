package com.zifuji.cloud.server.sys.module.area.qo;

import com.zifuji.cloud.base.bean.BasePageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class AreaPageQo extends BasePageQo {


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String type;

    private String name;





}
