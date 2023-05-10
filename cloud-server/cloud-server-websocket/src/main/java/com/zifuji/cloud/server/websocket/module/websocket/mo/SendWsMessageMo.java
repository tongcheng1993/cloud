package com.zifuji.cloud.server.websocket.module.websocket.mo;

import lombok.Data;

@Data
public class SendWsMessageMo {


	private String fromName;

	private Long fromUserId;

	private String businessType;

	private Long userId;

	private String userName;

	private Object obj;

}
