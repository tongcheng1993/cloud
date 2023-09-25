package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_setting_work_day")
public class ReservationSettingWorkDayEntity extends MyBaseEntity {

    private Long settingMainId;
    // 1 星期天 2 星期一 3 星期二 4 星期三 5 星期四 6 星期五 7 星期六
    private Integer dayNo;

    private String workFlag;

}
