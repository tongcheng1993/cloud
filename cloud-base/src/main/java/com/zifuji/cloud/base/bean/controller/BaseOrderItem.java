package com.zifuji.cloud.base.bean.controller;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseOrderItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String column;
	
	private Boolean asc = true;
}
