package com.zifuji.cloud.server.business.module.reservation.bo;

import lombok.Data;

@Data
public class ReservationTimeBo {

    private String dayName;

    private String startRange;

    private String endRange;

    private String timeRange;

    private int allNum;

    private int usedNum;

    private int haveNum;

}
