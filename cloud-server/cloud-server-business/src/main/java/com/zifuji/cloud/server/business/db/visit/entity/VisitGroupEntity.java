package com.zifuji.cloud.server.business.db.visit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_visit_group")
public class VisitGroupEntity extends MyBaseEntity {

    private String groupId;

    private String address;

    private LocalDate visitDate;
}
