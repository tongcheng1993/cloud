package com.zifuji.cloud.server.business.db.reservation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_reservation_apply_record")
public class ReservationApplyRecordEntity extends MyBaseEntity {

    private Long settingMainId;

    private String dayName;

    private String timeRange;
    // 传入的末尾是00:00
    private String startRange;
    // 传入的末尾是59:59
    private String endRange;

    private String recordNo;


}
