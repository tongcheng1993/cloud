package com.zifuji.cloud.server.sys.db.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_bu_edu_school")
public class EduSchoolEntity extends MyBaseEntity {

    private String schoolName;

    private String schoolCode;

    private String auditUnit;

    private String province;

    private String city;

    private String eduLevel;

    private String schoolNote;
}
