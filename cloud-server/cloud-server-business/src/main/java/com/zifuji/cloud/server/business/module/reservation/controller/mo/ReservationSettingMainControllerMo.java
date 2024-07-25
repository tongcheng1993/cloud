package com.zifuji.cloud.server.business.module.reservation.controller.mo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

import com.zifuji.cloud.server.base.db.BaseMo;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class ReservationSettingMainControllerMo extends BaseMo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
