package com.zifuji.cloud.base.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class BaseOrderItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String column;
	
	private Boolean asc = true;
}
