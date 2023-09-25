package com.zifuji.cloud.server.sys.db.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_quartz_record")
public class QuartzRecordEntity extends MyBaseEntity {

    private String name;

    private String description;

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String status;
}
