package com.zifuji.cloud.server.sys.db.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_sys_notice")
public class NoticeEntity extends MyBaseEntity {
	
	
	private String title;
	
	private String author;
	
	private String content;

}
