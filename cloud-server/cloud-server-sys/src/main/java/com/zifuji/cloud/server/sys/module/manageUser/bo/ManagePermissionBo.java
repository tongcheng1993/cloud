package com.zifuji.cloud.server.sys.module.manageUser.bo;

import com.zifuji.cloud.base.bean.BaseBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManagePermissionBo extends BaseBo {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;


    private String name;

    private String codeSys;

    private String codeModule;

    private String code;

    private String description;


}
