package com.zifuji.cloud.server.sys.module.manageUser.qo;

import com.zifuji.cloud.base.bean.BasePageQo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManagePermissionPageQo extends BasePageQo {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<Long> userId;


    private List<String> userName;

    private List<String> roleCode;
}
