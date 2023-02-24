package com.zifuji.cloud.server.sys.db.visit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_visit_member")
public class VisitMemberEntity extends MyBaseEntity {


    private String groupId;

    private String memberId;

    private String name;

    private String sex;

    private LocalDate birthday;



}
