package com.zifuji.cloud.server.sys.module.reservation.mo;

import com.zifuji.cloud.base.bean.BaseMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingMainMo extends BaseMo {

    @NotBlank(message = "唯一code不能为空")
    private String code;
    private Integer openDays;
    private String openAddr;
    private String description;
}
