package com.zifuji.cloud.server.sys.module.dic.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.server.base.bean.BaseControllerVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DicItemControllerVo extends BaseControllerVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;

	private String name;

	private String code;

	private String value;

	private Boolean disabled;
}
