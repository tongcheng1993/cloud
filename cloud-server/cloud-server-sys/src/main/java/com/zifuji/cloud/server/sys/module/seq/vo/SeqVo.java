package com.zifuji.cloud.server.sys.module.seq.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class SeqVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name;

	private String lastSeq;
}
