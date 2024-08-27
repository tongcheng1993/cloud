package com.zifuji.cloud.server.websocket.db.wsMessage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zifuji.cloud.server.base.module.mybatis.bean.MyBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "z_ws_message")
public class WsMessageEntity extends MyBaseEntity {

	private String businessType = "";

	private Long fromUserId = -1L;

	private String fromUserName = "";

	private Long toUserId = -1L;

	private String toUserName = "";

	private String typePath = "";

	private String obj = "";

}
