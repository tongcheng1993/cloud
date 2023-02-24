package com.zifuji.cloud.base.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MyWebsocketMessage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private LocalDateTime time;
	
	private String businessType;
	
	private Long userId;
	
	private Object obj;
	
	
	
	
	
}
