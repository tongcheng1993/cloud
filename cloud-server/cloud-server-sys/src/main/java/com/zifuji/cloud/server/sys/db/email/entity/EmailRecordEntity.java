package com.zifuji.cloud.server.sys.db.email.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_email_record")
public class EmailRecordEntity extends MyBaseEntity {

    private String fromName;

    private String addrFrom;

    private String toName;

    private String addrTo;

    private String subject;

    private String content;

    private Boolean sendStatus;

    private LocalDateTime sendTime;

    private String fileIdList;
}
