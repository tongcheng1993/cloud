package com.zifuji.cloud.server.sys.module.dic.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DicVo extends BaseVo{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	
	private String name;
	
	private String code;
	
	private String description;

	private List<DicItemVo> dicItemVoList;
}
