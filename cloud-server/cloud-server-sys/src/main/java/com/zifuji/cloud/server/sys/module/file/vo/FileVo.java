package com.zifuji.cloud.server.sys.module.file.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zifuji.cloud.base.bean.BaseVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FileVo extends BaseVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	@JsonSerialize(using = ToStringSerializer.class)
	private Long sortNum;
	
	
	private LocalDateTime createTime;
	
	
	private String fileName;
	
	private Long fileByteSize;

	private byte[] fileByte;
	
	private String fileUrl;
}