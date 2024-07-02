package com.zifuji.cloud.server.websocket.module.websocket.controller.mo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SendWsMessageMo {

	@NotBlank(message = "businessType不能为空")
	private String businessType;

    private Long fromUserId;

    private String fromUserName;

    private Long toUserId;

    private String toUserName;


    private String typePath;



    @NotNull(message = "obj不能为空")
    private String obj;

}
