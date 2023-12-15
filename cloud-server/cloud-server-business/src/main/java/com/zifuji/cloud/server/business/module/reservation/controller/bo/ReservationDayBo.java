package com.zifuji.cloud.server.business.module.reservation.controller.bo;

import lombok.Data;

import java.util.List;

@Data
public class ReservationDayBo {

    private String code;

    private Long settingMainId;

    private String dayName;

    private String dayStatus;

    private int allNum;

    private int usedNum;

    private int haveNum;

    private List<ReservationTimeBo> reservationTimeBoList;
}
