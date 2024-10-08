package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_setting_work_time")
public class ReservationSettingWorkTimeEntity extends MyBaseEntity {

    private Long settingMainId;

    private String startRange;

    private String endRange;

    private int allNum;



}
