package com.zifuji.cloud.server.sys.module.file.vo;

import com.zifuji.cloud.base.bean.controller.BaseControllerVo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(description = "")
public class FileControllerVo extends BaseControllerVo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String fileName;

	private String fileType;
	
	private Long fileByteSize;

	private byte[] fileByte;

	private String mimeType;
	
	private String fileUrl;

	private InputStream inputStream;

	// 文件上传来源地址
	private String uploadPath = "";
}
