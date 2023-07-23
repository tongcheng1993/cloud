package com.zifuji.cloud.server.business.module.reservation.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationApplyWorkTimeControllerVo extends BaseControllerVo {

    private String code;

    private String dayName;

    private String timeRange;

    private int allNum;

    private int usedNum;

    private int haveNum;

}
