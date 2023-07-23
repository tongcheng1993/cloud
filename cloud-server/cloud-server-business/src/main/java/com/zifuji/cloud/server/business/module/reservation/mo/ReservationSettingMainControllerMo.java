package com.zifuji.cloud.server.business.module.reservation.mo;

import com.zifuji.cloud.base.bean.controller.BaseControllerMo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingMainControllerMo extends BaseControllerMo {


    private Long id;

    @NotBlank(message = "唯一code不能为空")
    private String code;

    private String status;

    private Integer openDays;

    private String openAddr;

    private String description;
    // 星期工作日设置
    private List<ReservationSettingWorkDayMo> reservationSettingWorkDayMoList;
    // 开放时间设置
    private List<ReservationSettingWorkTimeMo> reservationSettingWorkTimeMoList;

}