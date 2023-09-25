package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_setting_main")
public class ReservationSettingMainEntity extends MyBaseEntity {

    private String code;

    private String status;

    private Integer openDays;

    private String openAddr;

    private String description;

}
