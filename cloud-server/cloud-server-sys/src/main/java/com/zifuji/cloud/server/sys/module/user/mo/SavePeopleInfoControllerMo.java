package com.zifuji.cloud.server.sys.module.user.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SavePeopleInfoControllerMo extends BaseControllerMo {

    private String peopleName;

    private String cardNumber;
}
