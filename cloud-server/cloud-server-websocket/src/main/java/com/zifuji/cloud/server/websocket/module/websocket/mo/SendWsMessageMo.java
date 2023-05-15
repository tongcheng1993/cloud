package com.zifuji.cloud.server.websocket.module.websocket.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SendWsMessageMo {


	private Long fromUserId;

	private String fromName;

	@NotNull(message = "userId不能为空")
	private Long userId;

	private String userName;

	@NotBlank(message = "businessType不能为空")
	private String businessType;

	@NotNull(message = "obj不能为空")
	private Object obj;

}
