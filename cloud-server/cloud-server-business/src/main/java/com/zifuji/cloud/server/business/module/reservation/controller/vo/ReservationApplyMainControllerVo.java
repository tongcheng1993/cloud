package com.zifuji.cloud.server.business.module.reservation.controller.vo;

import com.zifuji.cloud.server.base.bean.BaseControllerVo;
import com.zifuji.cloud.server.business.module.reservation.bo.ReservationDayBo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyMainControllerVo extends BaseControllerVo {

    private String code;

    private Integer openDays;

    private String openAddr;

    private String description;

    private List<ReservationDayBo> list;

}
