package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_setting_day_record")
public class ReservationSettingDayRecordEntity extends MyBaseEntity {

    private Long settingMainId;

    private String dayName;

    private String dayStatus;

    private int allNum;

    private int usedNum;

    private int haveNum;

}
