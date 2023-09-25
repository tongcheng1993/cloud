package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_setting_spe_day")
public class ReservationSettingSpeDayEntity extends MyBaseEntity {

    private Long settingMainId;

    private LocalDate speDay;

    private String workFlag;

}
