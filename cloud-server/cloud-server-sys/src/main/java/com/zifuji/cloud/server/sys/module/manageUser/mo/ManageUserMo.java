package com.zifuji.cloud.server.sys.module.manageUser.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ManageUserMo extends BaseMo {

    private Long id;
    @NotBlank(message = "")
    private String userName;
    @NotBlank(message = "")
    private String name;

    private String passWord;


}
