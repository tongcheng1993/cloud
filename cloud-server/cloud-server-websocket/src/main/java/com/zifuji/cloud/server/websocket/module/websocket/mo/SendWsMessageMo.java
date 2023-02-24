package com.zifuji.cloud.server.websocket.module.websocket.mo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SendWsMessageMo {

	@NotBlank(message = "")
	private String businessType;

	@NotNull(message = "")
	private Long userId;
	
	@NotNull(message = "")
	private Object obj;

}
