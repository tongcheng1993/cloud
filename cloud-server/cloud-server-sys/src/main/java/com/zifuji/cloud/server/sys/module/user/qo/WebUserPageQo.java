package com.zifuji.cloud.server.sys.module.user.qo;

import java.util.List;

import com.zifuji.cloud.server.base.bean.BaseControllerPageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class WebUserPageQo  extends BaseControllerPageQo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<String> userName;
}
