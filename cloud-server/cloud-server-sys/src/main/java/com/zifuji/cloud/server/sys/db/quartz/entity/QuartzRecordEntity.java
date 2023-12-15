package com.zifuji.cloud.server.sys.db.quartz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_quartz_record")
public class QuartzRecordEntity extends MyBaseEntity {
    // 名称
    private String name;
    // 描述
    private String description;
    // 定时任务类
    private String jobClassName;
    // 定时任务组
    private String jobGroupName;
    // cron表达式
    private String cronExpression;
    // 定时任务当前状态
    private Boolean status;
}
