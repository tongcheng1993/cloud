package com.zifuji.cloud.server.sys.db.email.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.db.entity.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_email_template")
public class EmailTemplateEntity extends MyBaseEntity {

	
	private String name;

	private String subject;

	private Long templateId;
}
