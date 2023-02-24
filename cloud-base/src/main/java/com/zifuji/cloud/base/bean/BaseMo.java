package com.zifuji.cloud.base.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseMo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Long id;
}
