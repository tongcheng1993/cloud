package com.zifuji.cloud.server.sys.db.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_notice_read")
public class NoticeReadEntity extends MyBaseEntity {

	
	private Long noticeId;
}
